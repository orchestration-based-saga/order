package com.saga.order.domain.service;

import com.saga.order.domain.in.OrderDomainServiceApi;
import com.saga.order.domain.model.*;
import com.saga.order.domain.model.enums.OrderDomainStatus;
import com.saga.order.domain.out.MerchantProductRepositoryApi;
import com.saga.order.domain.out.OrderProducerApi;
import com.saga.order.domain.out.OrderRepositoryApi;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

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
        Optional<Order> maybeOrder = orderRepositoryApi.findByOrderId(orderId);
        if (maybeProduct.isEmpty() || maybeOrder.isEmpty()) {
            return false;
        }
        if (!maybeProduct.get().serviceable()) {
            return false;
        }
        String businessKey = maybeOrder.get().orderId() + "-" +
                maybeProduct.get().merchantInventoryId() + "-" + RandomStringUtils.randomAlphanumeric(3).toUpperCase();
        orderProducerApi.createClaim(
                orderId,
                itemId,
                maybeProduct.get().merchantInventoryId(),
                maybeOrder.get().customerId(),
                maybeProduct.get().merchant().userId(),
                businessKey);
        return true;
    }

    @Override
    public boolean createOrder(CreateOrder orderRequest) {
        // group basket by merchant id
        Map<Merchant, List<Product>> productsGroupedByMerchant = groupBasketByMerchants(orderRequest.basket());
        // create order
        Order order = createOrder(orderRequest.customerId());
        // create suborders per merchant
        order = createSubordersPerMerchant(order, productsGroupedByMerchant);
        orderRepositoryApi.upsertOrder(order);
        orderProducerApi.send(order);
        return true;
    }

    @Override
    public void processPayment(Payment payment) {
        Optional<Order> maybeOrder = orderRepositoryApi.findByOrderId(payment.orderId());
        if (maybeOrder.isEmpty()) {
            throw new RuntimeException("Order with id " + payment.orderId() + " not found");
        }
        Order order = maybeOrder.get();
        order = order.updateStatus(OrderDomainStatus.COMPLETED);
        order = orderRepositoryApi.upsertOrder(order);
        orderProducerApi.send(order);
    }

    private Map<Merchant, List<Product>> groupBasketByMerchants(Set<BasketItem> basket) {
        Map<Merchant, List<Product>> productsGroupedByMerchant = new HashMap<>();
        for (BasketItem item : basket) {
            Optional<Product> maybeProduct = merchantProductRepositoryApi.findByIdWithMerchant(item.merchantInventoryId());
            if (maybeProduct.isEmpty()) {
                throw new RuntimeException("Product with id " + item.merchantInventoryId() + " not found");
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
        return productsGroupedByMerchant;
    }

    private Order createOrder(UUID customerId) {
        String orderId = RandomStringUtils.randomAlphanumeric(5) + "-" + RandomStringUtils.randomAlphanumeric(2);
        // faking a completed order by setting the status to COMPLETED and confirmedAt and packedAt
        Order order = new Order(
                null,
                OrderDomainStatus.PENDING,
                orderId,
                null,
                customerId,
                BigDecimal.ZERO,
                null,
                null
        );

        // create suborders per merchant
        order = orderRepositoryApi.upsertOrder(order);
        return order;
    }

    private Order createSubordersPerMerchant(Order order, Map<Merchant, List<Product>> productsGroupedByMerchant) {
        Set<Suborder> suborders = new HashSet<>();
        BigDecimal total = BigDecimal.ZERO;
        for (Map.Entry<Merchant, List<Product>> productsOfMerchant : productsGroupedByMerchant.entrySet()) {
            List<Product> products = productsOfMerchant.getValue();
            BigDecimal totalPriceOfSuborder = BigDecimal.ZERO;
            Set<SuborderItem> suborderItems = new HashSet<>();
            for (Product product : products) {
                totalPriceOfSuborder = totalPriceOfSuborder.add(product.price());
                suborderItems.add(new SuborderItem(null, product.merchantInventoryId()));
            }
            suborders.add(new Suborder(null, totalPriceOfSuborder, order, suborderItems, productsOfMerchant.getKey()));
            total = total.add(totalPriceOfSuborder);
        }
        suborders = orderRepositoryApi.createSuborders(suborders);
        order = order.setSuborders(suborders);
        order = order.setGrandTotal(total);
        order = order.setConfirmedAndPackedAt(LocalDateTime.now().minusDays(2), LocalDateTime.now());
        return order;
    }
}