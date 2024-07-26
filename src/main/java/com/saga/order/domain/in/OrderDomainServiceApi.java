package com.saga.order.domain.in;


import com.saga.order.domain.model.enums.OrderDomainStatus;

public interface OrderDomainServiceApi {

    void updateOrderStatus(Integer orderId, OrderDomainStatus orderStatus);

    boolean itemServicing(String orderId, Integer itemId);
}
