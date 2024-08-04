package com.saga.order.application.messaging.api;

import com.saga.order.domain.model.enums.OrderDomainStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record OrderMessage(
        Integer id,
        OrderDomainStatus status,
        String orderId,
        Set<SuborderMessage> suborders,
        UUID customerId,
        BigDecimal grandTotal,
        LocalDateTime confirmedAt,
        LocalDateTime packedAt
) {
}
