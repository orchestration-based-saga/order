package com.saga.order.infra.repository;

import com.cosium.spring.data.jpa.entity.graph.domain2.DynamicEntityGraph;
import com.saga.order.domain.model.Product;
import com.saga.order.domain.out.MerchantProductRepositoryApi;
import com.saga.order.infra.mapper.ProductEntityMapper;
import com.saga.order.infra.model.MerchantProductEntity;
import com.saga.order.infra.repository.jpa.MerchantProductEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MerchantProductRepository implements MerchantProductRepositoryApi {

    private final MerchantProductEntityRepository merchantProductEntityRepository;
    private final ProductEntityMapper mapper;

    @Override
    public Optional<Product> findByIdWithMerchant(Integer merchantInventoryId) {
        return merchantProductEntityRepository
                .findByMerchantInventoryId(merchantInventoryId,
                        DynamicEntityGraph.loading().addPath(MerchantProductEntity.Fields.merchant).build())
                .map(mapper::toDomain);
    }
}
