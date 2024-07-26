package com.saga.order.infra.mapper;

import com.saga.order.domain.model.Product;
import com.saga.order.infra.model.MerchantProductEntity;
import org.mapstruct.Mapper;

@Mapper
public interface ProductEntityMapper {

    Product toDomain(MerchantProductEntity productEntity);
}
