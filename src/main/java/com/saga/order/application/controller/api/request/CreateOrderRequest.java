package com.saga.order.application.controller.api.request;

import com.saga.order.domain.BasketItem;

import java.util.Set;
import java.util.UUID;

public record CreateOrderRequest(
        UUID customerId,
        Set<BasketItem> basket
) {
}
