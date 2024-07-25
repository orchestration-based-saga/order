package com.saga.order.infra.config;

import com.saga.order.domain.out.OrderRepositoryApi;
import com.saga.order.domain.service.OrderDomainServiceImpl;
import com.saga.order.domain.in.OrderDomainServiceApi;
import org.springframework.context.annotation.Bean;

public class ServiceBeanConfiguration {

    @Bean
    public OrderDomainServiceApi orderDomainServiceApi(OrderRepositoryApi orderRepositoryApi) {
        return new OrderDomainServiceImpl(orderRepositoryApi);
    }
}
