package com.saga.order.domain.out;

import com.saga.order.domain.model.Product;

import java.util.Optional;

public interface MerchantProductRepositoryApi {

    Optional<Product> findByIdWithMerchant(Integer merchantInventoryId);
}
