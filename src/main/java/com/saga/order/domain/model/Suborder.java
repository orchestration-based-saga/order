package com.saga.order.domain.model;

import java.math.BigDecimal;
import java.util.Set;

public record Suborder(
        Integer id,
        BigDecimal price,
        Order order,
        Set<SuborderItem> items
) {
}
