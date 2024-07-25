package com.saga.order.infra.mapper;

import com.saga.order.domain.model.enums.OrderDomainStatus;
import com.saga.order.infra.model.enums.OrderStatus;
import org.mapstruct.Mapper;

@Mapper
public interface OrderEntityMapper {

    OrderStatus fromDomain(OrderDomainStatus status);
}
