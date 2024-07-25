package com.saga.order.application.service;

import com.saga.order.application.mapper.OrderResponseMapper;
import com.saga.order.application.messaging.api.OrderStatusChange;
import com.saga.order.domain.in.OrderDomainServiceApi;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private  OrderDomainServiceApi orderDomainServiceApi;
    private OrderResponseMapper orderResponseMapper;

    public void updateStatus(OrderStatusChange statusChange) {
        orderDomainServiceApi.updateOrderStatus(statusChange.id(), orderResponseMapper.toDomain(statusChange.status()));
    }
}
