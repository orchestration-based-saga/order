package com.saga.order.application.controller.api.request;

public record ServiceableItemRequest(
        String orderId,
        Integer itemId
) {
}
