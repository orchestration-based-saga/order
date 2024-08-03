package com.saga.order.application.messaging.api;

import java.util.UUID;

public record CreateClaim(
        String orderId,
        Integer itemId,
        Integer merchantInventoryId,
        UUID customerId,
        UUID recipientId
) {
}
