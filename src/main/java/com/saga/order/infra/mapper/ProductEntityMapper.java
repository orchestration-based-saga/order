package com.saga.order.infra.mapper;

import com.saga.order.domain.model.Merchant;
import com.saga.order.domain.model.Product;
import com.saga.order.infra.model.MerchantEntity;
import com.saga.order.infra.model.MerchantProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ProductEntityMapper {

    Product toDomain(MerchantProductEntity productEntity);

    Merchant toDomain(MerchantEntity merchantEntity);

    @Mapping(target = "description", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "pickupAddress", ignore = true)
    @Mapping(target = "returnAddress", ignore = true)
    MerchantProductEntity toProductEntity(Product product);

    @Mapping(target = "tin", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "pickupAddress", ignore = true)
    @Mapping(target = "returnAddress", ignore = true)
    MerchantEntity toMerchantEntity(Merchant merchant);
}
