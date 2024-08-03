package com.saga.order.infra.repository.jpa;

import com.saga.order.infra.model.MerchantProductEntity;
import com.saga.order.infra.model.OrderEntity;
import com.saga.order.infra.model.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OrderEntityRepository extends JpaRepository<OrderEntity, Integer> {

    @Query("""
                UPDATE Orders o
                SET o.orderStatus = :status
                WHERE o.id = :id
            """
    )
    void updateOrderStatus(@Param("id") Integer orderId, @Param("status") OrderStatus orderStatus);

    @Query("""
                SELECT p
                FROM MerchantProduct p
                JOIN SuborderItem si ON si.merchantProduct.merchantInventoryId = p.merchantInventoryId
                JOIN Suborder s ON s.id = si.suborder.id
                JOIN Orders o ON o.id = s.order.id
                WHERE si.id = :itemId AND o.orderId = :orderId
            """
    )
    Optional<MerchantProductEntity> findByOrderIdAndSuborderItemId(@Param("orderId") String orderId, @Param("itemId") Integer itemId);

    Optional<OrderEntity> findByOrderId(String orderId);
}
