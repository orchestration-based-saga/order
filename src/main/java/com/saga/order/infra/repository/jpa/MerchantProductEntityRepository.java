package com.saga.order.infra.repository.jpa;

import com.cosium.spring.data.jpa.entity.graph.domain2.EntityGraph;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.saga.order.infra.model.MerchantProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MerchantProductEntityRepository extends JpaRepository<MerchantProductEntity, Integer>,
        EntityGraphJpaRepository<MerchantProductEntity, Integer> {

    Optional<MerchantProductEntity> findByMerchantInventoryId(Integer id, EntityGraph entityGraph);
}
