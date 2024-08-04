package com.saga.order.application.api.event;

import com.saga.order.application.api.enums.StatusChange;

public record OrderStatusChange(
    Integer id,
    StatusChange status
) {
}
