package com.saga.order.domain.service;

import com.saga.order.domain.BasketItem;
import com.saga.order.domain.in.OrderDomainServiceApi;
import com.saga.order.domain.model.*;
import com.saga.order.domain.model.enums.OrderDomainStatus;
import com.saga.order.domain.out.MerchantProductRepositoryApi;
import com.saga.order.domain.out.OrderProducerApi;
import com.saga.order.domain.out.OrderRepositoryApi;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@RequiredArgsConstructor
public class OrderDomainServiceImpl implements OrderDomainServiceApi {
    private final OrderRepositoryApi orderRepositoryApi;
    private final OrderProducerApi orderProducerApi;
    private final MerchantProductRepositoryApi merchantProductRepositoryApi;

    @Override
    public void updateOrderStatus(Integer orderId, OrderDomainStatus orderStatus) {
        orderRepositoryApi.updateStatus(orderId, orderStatus);
    }

    @Override
    public boolean itemServicing(String orderId, Integer itemId) {
        Optional<Product> maybeProduct = orderRepositoryApi.findByOrderIdAndItemId(orderId, itemId);
        if (maybeProduct.isEmpty()) {
            return false;
        }
        if (!maybeProduct.get().serviceable()) {
            return false;
        }
        orderProducerApi.createClaim(orderId, itemId, maybeProduct.get().merchantInventoryId());
        return true;
    }

    @Override
    @Transactional
    public boolean createOrder(CreateOrder orderRequest) {
        // group basket by merchant id
        Map<Merchant, List<Product>> productsGroupedByMerchant = new HashMap<>();
        for (BasketItem item : orderRequest.basket()) {
            Optional<Product> maybeProduct = merchantProductRepositoryApi.findByIdWithMerchant(item.merchantInventoryId());
            if (maybeProduct.isEmpty()) {
                return false;
            }
            Product product = maybeProduct.get();
            if (product.stockLevel() >= item.amount()) {
                product = product.calculateReservedLevel(item.amount());
            }
            if (productsGroupedByMerchant.get(product.merchant()) == null) {
                productsGroupedByMerchant.put(product.merchant(), new ArrayList<>(List.of(product)));
            } else {
                productsGroupedByMerchant.get(product.merchant()).add(product);
            }
        }
        // create order
        String orderId = RandomStringUtils.randomAlphanumeric(5) + "-" + RandomStringUtils.randomAlphanumeric(2);
        // faking a completed order by setting the status to COMPLETED and confirmedAt and packedAt
        Order order = new Order(
                null,
                OrderDomainStatus.PENDING,
                orderId,
                null,
                orderRequest.customerId(),
                BigDecimal.ZERO,
                null,
                null,
                null
        );
        // todo set product stock level when order is processed
        // create suborders per merchant
        order = orderRepositoryApi.upsertOrder(order);
        Set<Suborder> suborders = new HashSet<>();
        BigDecimal total = BigDecimal.ZERO;
        for (Map.Entry<Merchant, List<Product>> productsOfMerchant : productsGroupedByMerchant.entrySet()) {
            List<Product> products = productsOfMerchant.getValue();
            BigDecimal totalPriceOfSuborder = BigDecimal.ZERO;
            Set<SuborderItem> suborderItems = new HashSet<>();
            for (Product product : products) {
                totalPriceOfSuborder = totalPriceOfSuborder.add(product.price());
                suborderItems.add(new SuborderItem(product.merchantInventoryId()));
            }
            suborders.add(new Suborder(null, totalPriceOfSuborder, order, suborderItems));
            total = total.add(totalPriceOfSuborder);
        }
        suborders = orderRepositoryApi.createSuborders(suborders);
        order = order.setSuborders(suborders);
        order = order.setGrandTotal(total);
        order = order.setConfirmedAndPackedAt(LocalDateTime.now().minusDays(2), LocalDateTime.now());
        orderRepositoryApi.upsertOrder(order);
        orderProducerApi.send(order);
        return true;
    }
}