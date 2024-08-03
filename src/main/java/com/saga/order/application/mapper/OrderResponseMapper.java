package com.saga.order.application.mapper;

import com.saga.order.application.controller.api.request.CreateOrderRequest;
import com.saga.order.application.messaging.api.StatusChange;
import com.saga.order.domain.model.CreateOrder;
import com.saga.order.domain.model.enums.OrderDomainStatus;
import org.mapstruct.Mapper;

@Mapper
public interface OrderResponseMapper {

    OrderDomainStatus toDomain(StatusChange change);

    CreateOrder toDomain(CreateOrderRequest createOrder);
}
