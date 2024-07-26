package com.saga.order.domain.model;

import com.saga.order.domain.model.enums.OrderDomainStatus;

import java.util.Set;

public record Order(
        OrderDomainStatus status,
        String orderId,
        Set<Suborder> suborders
) {
}
