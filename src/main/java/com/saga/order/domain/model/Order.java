package com.saga.order.domain.model;

import com.saga.order.domain.model.enums.OrderDomainStatus;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Builder
public record Order(
        Integer id,
        OrderDomainStatus status,
        String orderId,
        Set<Suborder> suborders,
        UUID customerId,
        BigDecimal grandTotal,
        LocalDateTime confirmedAt,
        LocalDateTime packedAt,
        LocalDateTime cancellationDate
) {

    public Order setSuborders(Set<Suborder> suborders) {
        return new Order(id, status, orderId, suborders, customerId, grandTotal, confirmedAt, packedAt, cancellationDate);
    }

    public Order setGrandTotal(BigDecimal total) {
        return new Order(id, status, orderId, suborders, customerId, total, confirmedAt, packedAt, cancellationDate);
    }

    public Order setConfirmedAndPackedAt(LocalDateTime confirmedAt, LocalDateTime packedAt) {
        return new Order(id, status, orderId, suborders, customerId, grandTotal, confirmedAt, packedAt, cancellationDate);
    }

    public Order updateStatus(OrderDomainStatus status) {
        return new Order(id, status, orderId, suborders, customerId, grandTotal, confirmedAt, packedAt, cancellationDate);
    }
}
