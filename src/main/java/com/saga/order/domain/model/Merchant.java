package com.saga.order.domain.model;

import java.util.UUID;

public record Merchant(
        Integer id,
        UUID userId
) {
}
