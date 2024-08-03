package com.saga.order.domain;

public record BasketItem(
        Integer merchantInventoryId,
        Integer amount
) {
}
