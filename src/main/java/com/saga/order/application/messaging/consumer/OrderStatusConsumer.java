package com.saga.order.application.messaging.consumer;

import com.saga.order.application.messaging.api.OrderStatusChange;
import com.saga.order.application.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
public class OrderStatusConsumer {
    private final OrderService orderService;

    @Bean
    public Consumer<Message<OrderStatusChange>> orderStatus(){
        return (message) -> orderService.updateStatus(message.getPayload());
    }
}
