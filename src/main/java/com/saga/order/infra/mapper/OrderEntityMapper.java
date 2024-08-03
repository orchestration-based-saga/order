package com.saga.order.infra.mapper;

import com.saga.order.domain.model.Order;
import com.saga.order.domain.model.Suborder;
import com.saga.order.domain.model.SuborderItem;
import com.saga.order.domain.model.enums.OrderDomainStatus;
import com.saga.order.infra.model.OrderEntity;
import com.saga.order.infra.model.SuborderEntity;
import com.saga.order.infra.model.SuborderItemEntity;
import com.saga.order.infra.model.enums.OrderStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public abstract class OrderEntityMapper {

    public abstract OrderDomainStatus toOrderDomain(OrderStatus status);

    public abstract OrderStatus toOrderStatusEntity(OrderDomainStatus status);

    @Mapping(target = "orderStatus", source = "status")
    @Mapping(target = "paymentMethod", expression = "java(com.saga.order.infra.model.enums.PaymentMethod.CARD)")
    @Mapping(target = "currency", expression = "java(com.saga.order.infra.model.enums.Currency.RSD)")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    public abstract OrderEntity toEntity(Order order);

    @Mapping(target = "status", source = "orderStatus")
    public abstract Order toOrderDomain(OrderEntity order);

    public abstract Set<Suborder> toSubordersDomain(Set<SuborderEntity> suborders);

    @Mapping(target = "suborderItems", ignore = true)
    public abstract SuborderEntity toEntity(Suborder suborder);

    @Mapping(target = "items", source = "suborderItems")
    public abstract Suborder toDomain(SuborderEntity suborder);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "suborder", ignore = true)
    @Mapping(target = "merchantProduct.merchantInventoryId", source = "merchantInventoryId")
    public abstract SuborderItemEntity toSuborderItemEntity(SuborderItem suborderItem);

    @Mapping(target = "merchantInventoryId", source = "suborderItem.merchantProduct.merchantInventoryId")
    public abstract SuborderItem toSuborderItemDomain(SuborderItemEntity suborderItem);

    public Set<SuborderItemEntity> toSuborderItemEntities(Set<SuborderItem> suborderItems) {
        if (suborderItems == null) {
            return new HashSet<>();
        }
        return suborderItems.stream()
                .map(this::toSuborderItemEntity)
                .collect(Collectors.toSet());
    }

    public SuborderEntity mapWithSuborderItems(Suborder suborder, Set<SuborderItemEntity> suborderItems) {
        SuborderEntity suborderEntity = this.toEntity(suborder);
        suborderEntity.setSuborderItems(new HashSet<>());
        suborderItems.forEach(suborderEntity::addSuborderItem);
        return suborderEntity;
    }

}
