package com.saga.order.domain.service;

import com.saga.order.domain.in.OrderDomainServiceApi;
import com.saga.order.domain.model.Product;
import com.saga.order.domain.model.enums.OrderDomainStatus;
import com.saga.order.domain.out.ClaimProducerApi;
import com.saga.order.domain.out.OrderRepositoryApi;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class OrderDomainServiceImpl implements OrderDomainServiceApi {
    private final OrderRepositoryApi orderRepositoryApi;
    private final ClaimProducerApi claimProducerApi;

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
        if(!maybeProduct.get().serviceable()){
            return false;
        }
        claimProducerApi.createClaim(orderId, itemId, maybeProduct.get().merchantInventoryId());
        return true;
    }
}