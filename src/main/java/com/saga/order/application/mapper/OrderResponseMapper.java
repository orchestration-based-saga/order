package com.saga.order.application.mapper;

import com.saga.order.application.api.request.CreateOrderRequest;
import com.saga.order.application.api.event.OrderMessage;
import com.saga.order.application.api.event.PaymentMessage;
import com.saga.order.application.api.event.SuborderItemMessage;
import com.saga.order.application.api.event.SuborderMessage;
import com.saga.order.application.api.enums.StatusChange;
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
