package com.saga.order.domain.service;

import com.saga.order.domain.in.OrderDomainServiceApi;
import com.saga.order.domain.model.enums.OrderDomainStatus;
import com.saga.order.domain.out.OrderRepositoryApi;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderDomainServiceImpl implements OrderDomainServiceApi {
    private final OrderRepositoryApi orderRepositoryApi;

    @Override
    public void updateOrderStatus(Integer orderId, OrderDomainStatus orderStatus) {
        orderRepositoryApi.updateStatus(orderId, orderStatus);
    }
}
