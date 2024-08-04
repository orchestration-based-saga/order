package com.saga.order.application.messaging.consumer;

import com.saga.order.application.mapper.OrderResponseMapper;
import com.saga.order.application.api.event.PaymentMessage;
import com.saga.order.domain.in.OrderDomainServiceApi;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
public class PaymentConsumer {
    private final OrderDomainServiceApi orderDomainServiceApi;
    private final OrderResponseMapper orderResponseMapper;

    @Bean
    public Consumer<Message<PaymentMessage>> payment() {
        return message -> orderDomainServiceApi.processPayment(orderResponseMapper.toDomain(message.getPayload()));
    }
}
