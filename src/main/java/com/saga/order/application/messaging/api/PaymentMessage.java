package com.saga.order.application.messaging.api;

import java.math.BigDecimal;
import java.util.UUID;

public record PaymentMessage (
        UUID id,
        BigDecimal transactionAmount,
        BigDecimal paidAmount
){
}
