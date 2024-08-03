package com.saga.order.domain.out;

import com.saga.order.domain.model.Order;

import java.util.UUID;

public interface OrderProducerApi {

    void createClaim(String orderId, Integer itemId, Integer merchantInventoryId, UUID customerId, UUID recipientId);

    void send(Order order);
}
