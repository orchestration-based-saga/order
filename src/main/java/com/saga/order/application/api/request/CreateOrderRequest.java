package com.saga.order.application.api.request;

import com.saga.order.domain.model.BasketItem;

import java.util.Set;
import java.util.UUID;

public record CreateOrderRequest(
        UUID customerId,
        Set<BasketItem> basket
) {
}
