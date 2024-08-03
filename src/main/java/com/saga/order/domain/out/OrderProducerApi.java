package com.saga.order.domain.out;

import com.saga.order.domain.model.Order;

public interface OrderProducerApi {

    void createClaim(String orderId, Integer itemId, Integer merchantInventoryId);

    void send(Order order);
}
