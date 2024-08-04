package com.saga.order.domain.model;

public record BasketItem(
        Integer merchantInventoryId,
        Integer amount
) {
}
