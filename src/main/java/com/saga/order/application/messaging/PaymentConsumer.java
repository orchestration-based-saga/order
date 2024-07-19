package com.saga.order.application.messaging;

import com.saga.order.application.messaging.api.PaymentMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
public class PaymentConsumer {

    @Bean
    public Consumer<Message<PaymentMessage>> payment() {
        return message -> System.out.println(message.getPayload());
    }
}
