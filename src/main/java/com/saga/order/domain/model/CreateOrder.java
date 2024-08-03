package com.saga.order.domain.model;

import com.saga.order.domain.BasketItem;

import java.util.Set;
import java.util.UUID;

public record CreateOrder(
        UUID customerId,
        Set<BasketItem> basket
) {
}
