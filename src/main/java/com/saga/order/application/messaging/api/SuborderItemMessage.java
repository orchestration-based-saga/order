package com.saga.order.application.messaging.api;

public record SuborderItemMessage(
        Integer id,
        Integer merchantInventoryId
) {
}
