package com.saga.order.application.api.request;

public record ServiceableItemRequest(
        String orderId,
        Integer itemId
) {
}
