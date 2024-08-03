package com.saga.order.application.service;

import com.saga.order.application.controller.api.request.CreateOrderRequest;
import com.saga.order.application.controller.api.request.ServiceableItemRequest;
import com.saga.order.application.controller.api.response.ServiceScheduledResponse;
import com.saga.order.application.mapper.OrderResponseMapper;
import com.saga.order.application.messaging.api.OrderStatusChange;
import com.saga.order.domain.in.OrderDomainServiceApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderDomainServiceApi orderDomainServiceApi;
    private final OrderResponseMapper orderResponseMapper;

    public void updateStatus(OrderStatusChange statusChange) {
        orderDomainServiceApi.updateOrderStatus(statusChange.id(), orderResponseMapper.toDomain(statusChange.status()));
    }

    public ServiceScheduledResponse scheduleItemServicing(ServiceableItemRequest request){
        if (!orderDomainServiceApi.itemServicing(request.orderId(), request.itemId())) {
            return new ServiceScheduledResponse("Item isn't serviceable", false);
        }
        return new ServiceScheduledResponse("Item servicing scheduled", true);
    }

    public boolean createOrder(CreateOrderRequest request) {
        return orderDomainServiceApi.createOrder(orderResponseMapper.toDomain(request));
    }
}
