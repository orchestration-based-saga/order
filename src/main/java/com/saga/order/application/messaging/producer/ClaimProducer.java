package com.saga.order.application.messaging.producer;

import com.saga.order.application.messaging.api.CreateClaim;
import com.saga.order.domain.out.ClaimProducerApi;
import com.saga.order.infra.common.event.StreamBindingConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClaimProducer implements ClaimProducerApi {

    private final StreamBridge streamBridge;

    @Override
    public void createClaim(String orderId, Integer itemId, Integer merchantInventoryId) {
        CreateClaim claim = new CreateClaim(orderId, itemId, merchantInventoryId);
        streamBridge.send(StreamBindingConstants.CLAIM, MessageBuilder.withPayload(claim).build());
    }
}
