package com.saga.order.application.api.event;

import lombok.Builder;

@Builder
public record WorkflowStartProcessMessage(
        String processId,
        String businessKey,
        Object data
) {
}
