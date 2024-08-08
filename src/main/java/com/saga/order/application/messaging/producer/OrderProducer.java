package com.saga.order.application.messaging.producer;

import com.saga.order.application.api.enums.WorkflowConstants;
import com.saga.order.application.api.event.WorkflowStartProcessMessage;
import com.saga.order.application.mapper.OrderResponseMapper;
import com.saga.order.application.api.event.CreateClaimMessage;
import com.saga.order.application.api.event.OrderMessage;
import com.saga.order.domain.model.Order;
import com.saga.order.domain.out.OrderProducerApi;
import com.saga.order.infra.common.event.StreamBindingConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderProducer implements OrderProducerApi {

    private final StreamBridge streamBridge;
    private final OrderResponseMapper orderResponseMapper;

    @Override
    public void createClaim(
            String orderId,
            Integer itemId,
            Integer merchantInventoryId,
            UUID customerId,
            UUID recipientId,
            String businessKey) {
        CreateClaimMessage claim = new CreateClaimMessage(orderId, itemId, merchantInventoryId, customerId, recipientId);
        WorkflowStartProcessMessage message = WorkflowStartProcessMessage.builder()
                .processId(WorkflowConstants.ITEM_SERVICING)
                .businessKey(businessKey)
                .data(claim)
                .build();
        streamBridge.send(StreamBindingConstants.START_PROCESS, MessageBuilder.withPayload(message).build());
    }

    @Override
    public void send(Order order) {
        OrderMessage orderMessage = orderResponseMapper.toMessage(order);
        streamBridge.send(StreamBindingConstants.ORDER, MessageBuilder.withPayload(orderMessage).build());
    }
}
