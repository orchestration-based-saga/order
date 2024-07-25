package com.saga.order.domain.out;

import com.saga.order.domain.model.enums.OrderDomainStatus;

public interface OrderRepositoryApi {
    void updateStatus(Integer orderId, OrderDomainStatus orderStatus);
}
