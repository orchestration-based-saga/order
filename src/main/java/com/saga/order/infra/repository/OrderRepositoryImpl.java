package com.saga.order.infra.repository;

import com.cosium.spring.data.jpa.entity.graph.domain2.DynamicEntityGraph;
import com.cosium.spring.data.jpa.entity.graph.domain2.EntityGraph;
import com.saga.order.domain.model.Order;
import com.saga.order.domain.model.Product;
import com.saga.order.domain.model.enums.OrderDomainStatus;
import com.saga.order.domain.out.OrderRepositoryApi;
import com.saga.order.infra.mapper.OrderEntityMapper;
import com.saga.order.infra.mapper.ProductEntityMapper;
import com.saga.order.infra.model.OrderEntity;
import com.saga.order.infra.model.SuborderEntity;
import com.saga.order.infra.repository.jpa.OrderEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepositoryApi {
    private final OrderEntityRepository orderEntityRepository;
    private final OrderEntityMapper orderEntityMapper;
    private final ProductEntityMapper productEntityMapper;

    @Override
    public void updateStatus(Integer orderId, OrderDomainStatus orderStatus) {
        orderEntityRepository.updateOrderStatus(orderId, orderEntityMapper.fromDomain(orderStatus));
    }

    @Override
    public Optional<Product> findByOrderIdAndItemId(String orderId, Integer itemId) {
        return orderEntityRepository.findByOrderIdAndSuborderItemId(
                orderId,
                itemId)
                .map(productEntityMapper::toDomain);
    }
}
