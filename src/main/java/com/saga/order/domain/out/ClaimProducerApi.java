package com.saga.order.domain.out;

public interface ClaimProducerApi {

    void createClaim(String orderId, Integer itemId, Integer merchantInventoryId);
}
