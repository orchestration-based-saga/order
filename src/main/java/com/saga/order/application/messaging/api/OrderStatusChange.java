package com.saga.order.application.messaging.api;

import com.saga.order.application.messaging.api.enums.StatusChange;

public record OrderStatusChange(
    Integer id,
    StatusChange status
) {
}
