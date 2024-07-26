package com.saga.order.infra.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity(name = "Suborder")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SuborderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    OrderEntity order;
    @OneToOne
    @JoinColumn(name = "merchant_inventory_id", referencedColumnName = "merchant_inventory_id")
    MerchantProductEntity merchantProduct;
    BigDecimal price;
    Integer amount;
    @OneToMany(mappedBy = "suborder")
    Set<SuborderItemEntity> suborderItems;
}
