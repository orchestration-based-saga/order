package com.saga.order.domain.out;

import com.saga.order.domain.model.Product;
import com.saga.order.domain.model.enums.OrderDomainStatus;

import java.util.Optional;

public interface OrderRepositoryApi {

    void updateStatus(Integer orderId, OrderDomainStatus orderStatus);

    Optional<Product> findByOrderIdAndItemId(String orderId, Integer itemId);
}
