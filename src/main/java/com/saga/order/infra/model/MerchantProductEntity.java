package com.saga.order.infra.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.math.BigDecimal;

@Entity(name = "MerchantProduct")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldNameConstants
public class MerchantProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "merchant_inventory_id")
    Integer merchantInventoryId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "merchant_id", referencedColumnName = "id")
    MerchantEntity merchant;
    String name;
    String description;
    Boolean serviceable;
    @OneToOne
    @JoinColumn(name = "pickup_address", referencedColumnName = "id")
    MerchantAddressEntity pickupAddress;
    @OneToOne
    @JoinColumn(name = "return_address", referencedColumnName = "id")
    MerchantAddressEntity returnAddress;
    Integer stockLevel;
    Integer reservedLevel;
    BigDecimal price;
}
