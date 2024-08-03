package com.saga.order.infra.repository.jpa;

import com.saga.order.infra.model.SuborderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuborderEntityRepository extends JpaRepository<SuborderEntity, Integer> {
}
