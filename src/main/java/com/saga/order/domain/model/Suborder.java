package com.saga.order.domain.model;

public record Suborder(
        Integer amount,
        Product product
) {
}
