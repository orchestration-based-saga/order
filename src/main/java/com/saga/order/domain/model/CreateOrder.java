package com.saga.order.domain.model;

import java.util.Set;
import java.util.UUID;

public record CreateOrder(
        UUID customerId,
        Set<BasketItem> basket
) {
}
