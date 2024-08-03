package com.saga.order.infra.config;

import com.saga.order.domain.in.OrderDomainServiceApi;
import com.saga.order.domain.out.MerchantProductRepositoryApi;
import com.saga.order.domain.out.OrderProducerApi;
import com.saga.order.domain.out.OrderRepositoryApi;
import com.saga.order.domain.service.OrderDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceBeanConfiguration {

    @Bean
    public OrderDomainServiceApi orderDomainServiceApi(
            OrderRepositoryApi orderRepositoryApi,
            OrderProducerApi orderProducerApi,
            MerchantProductRepositoryApi merchantProductRepositoryApi) {
        return new OrderDomainServiceImpl(orderRepositoryApi, orderProducerApi, merchantProductRepositoryApi);
    }
}
