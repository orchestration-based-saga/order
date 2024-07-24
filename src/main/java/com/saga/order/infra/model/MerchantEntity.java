package com.saga.order.infra.model;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Merchant")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MerchantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String tin;
    String name;
    @OneToOne
    @JoinColumn(name = "pickup_address", referencedColumnName = "id")
    MerchantAddressEntity pickupAddress;
    @OneToOne
    @JoinColumn(name = "return_address", referencedColumnName = "id")
    MerchantAddressEntity returnAddress;
}
