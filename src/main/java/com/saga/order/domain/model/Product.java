package com.saga.order.domain.model;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record Product(
        Integer merchantInventoryId,
        String name,
        String ean,
        Boolean serviceable,
        Integer stockLevel,
        Integer reservedLevel,
        Merchant merchant,
        BigDecimal price
) {

    public Product calculateReservedLevel(Integer amount){
        Integer totalReserved = amount + reservedLevel;
        return new Product(merchantInventoryId, name, ean, serviceable, stockLevel, totalReserved, merchant, price);
    }
}
