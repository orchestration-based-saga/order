package com.saga.order.infra.repository.jpa;

import com.saga.order.infra.model.OrderEntity;
import com.saga.order.infra.model.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderEntityRepository extends JpaRepository<OrderEntity, Integer> {

    @Query("""
        UPDATE Order o
        SET o.orderStatus = :status
        WHERE o.id = :id
    """
    )
    void updateOrderStatus(@Param("id") Integer orderId, @Param("status") OrderStatus orderStatus);
}
