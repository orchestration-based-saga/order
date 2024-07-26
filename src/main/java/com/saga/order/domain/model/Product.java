package com.saga.order.domain.model;

public record Product(
        Integer merchantInventoryId,
        String name,
        String ean,
        Boolean serviceable
) {
}
