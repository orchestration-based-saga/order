package com.saga.order.domain.model;

import com.saga.order.domain.model.enums.PaymentStatus;

import java.util.UUID;

public record Payment(
        UUID id,
        PaymentStatus status,
        UUID transactionId,
        String orderId
) {
}
