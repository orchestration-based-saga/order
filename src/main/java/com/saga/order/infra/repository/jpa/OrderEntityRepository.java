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
                JOIN Suborder s ON s.merchantProduct.merchantInventoryId = p.merchantInventoryId
                JOIN Orders o ON o.id = s.order.id
                JOIN SuborderItem si ON si.suborder.id = s.id
                WHERE si.id = :itemId AND o.orderId = :orderId
            """
    )
    Optional<MerchantProductEntity> findByOrderIdAndSuborderItemId(@Param("orderId") String orderId, @Param("itemId") Integer itemId);
}
