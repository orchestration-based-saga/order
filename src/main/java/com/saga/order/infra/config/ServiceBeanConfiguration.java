package com.saga.order.infra.config;

import com.saga.order.domain.out.ClaimProducerApi;
import com.saga.order.domain.out.OrderRepositoryApi;
import com.saga.order.domain.service.OrderDomainServiceImpl;
import com.saga.order.domain.in.OrderDomainServiceApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceBeanConfiguration {

    @Bean
    public OrderDomainServiceApi orderDomainServiceApi(
            OrderRepositoryApi orderRepositoryApi, ClaimProducerApi claimProducerApi) {
        return new OrderDomainServiceImpl(orderRepositoryApi, claimProducerApi);
    }
}
