package com.saga.order.application.messaging.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.saga.order.application.messaging.api.enums.TransactionStatus;

import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PaymentMessage(
        UUID id,
        TransactionStatus status,
        UUID transactionId,
        String orderId
) {
}
