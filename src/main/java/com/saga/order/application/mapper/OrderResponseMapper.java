package com.saga.order.application.mapper;

import com.saga.order.application.controller.api.request.CreateOrderRequest;
import com.saga.order.application.messaging.api.OrderMessage;
import com.saga.order.application.messaging.api.PaymentMessage;
import com.saga.order.application.messaging.api.SuborderItemMessage;
import com.saga.order.application.messaging.api.SuborderMessage;
import com.saga.order.application.messaging.api.enums.StatusChange;
import com.saga.order.domain.model.*;
import com.saga.order.domain.model.enums.OrderDomainStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface OrderResponseMapper {

    OrderDomainStatus toDomain(StatusChange change);

    CreateOrder toDomain(CreateOrderRequest createOrder);

    Payment toDomain(PaymentMessage paymentMessage);

    OrderMessage toMessage(Order order);

    @Mapping(target = "merchantId", source = "merchant.id")
    SuborderMessage toMessage(Suborder suborder);

    SuborderItemMessage toMessage(SuborderItem order);
}
