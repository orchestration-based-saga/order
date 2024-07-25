package com.saga.order.infra.repository;

import com.saga.order.domain.model.enums.OrderDomainStatus;
import com.saga.order.domain.out.OrderRepositoryApi;
import com.saga.order.infra.mapper.OrderEntityMapper;
import com.saga.order.infra.repository.jpa.OrderEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepositoryApi {
    private final OrderEntityRepository orderEntityRepository;
    private final OrderEntityMapper orderEntityMapper;

    @Override
    public void updateStatus(Integer orderId, OrderDomainStatus orderStatus) {
        orderEntityRepository.updateOrderStatus(orderId, orderEntityMapper.fromDomain(orderStatus));
    }
}
