package com.saga.order.application.messaging.api;

public record OrderStatusChange(
    Integer id,
    StatusChange status
) {
}
