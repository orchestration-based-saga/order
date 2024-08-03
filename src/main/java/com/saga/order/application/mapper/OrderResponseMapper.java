package com.saga.order.application.mapper;

import com.saga.order.application.controller.api.request.CreateOrderRequest;
import com.saga.order.application.messaging.api.PaymentMessage;
import com.saga.order.application.messaging.api.enums.StatusChange;
import com.saga.order.domain.model.CreateOrder;
import com.saga.order.domain.model.Payment;
import com.saga.order.domain.model.enums.OrderDomainStatus;
import org.mapstruct.Mapper;

@Mapper
public interface OrderResponseMapper {

    OrderDomainStatus toDomain(StatusChange change);

    CreateOrder toDomain(CreateOrderRequest createOrder);

    Payment toDomain(PaymentMessage paymentMessage);
}
