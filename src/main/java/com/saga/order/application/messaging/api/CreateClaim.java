package com.saga.order.application.messaging.api;

public record CreateClaim(
        String orderId,
        Integer itemId,
        Integer merchantInventoryId
) {
}
