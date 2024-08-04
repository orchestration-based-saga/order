package com.saga.order.application.api.event;

public record SuborderItemMessage(
        Integer id,
        Integer merchantInventoryId
) {
}
