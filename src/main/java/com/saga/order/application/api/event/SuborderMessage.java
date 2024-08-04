package com.saga.order.application.api.event;

import com.saga.order.domain.model.Order;
import com.saga.order.domain.model.SuborderItem;

import java.math.BigDecimal;
import java.util.Set;

public record SuborderMessage(
        Integer id,
        BigDecimal price,
        Order order,
        Set<SuborderItem> items,
        Integer merchantId
) {
}
