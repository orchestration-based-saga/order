package com.saga.order.infra.model;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "SuborderItem")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SuborderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    Integer merchantInventoryId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "suborder_id", referencedColumnName = "id")
    SuborderEntity suborder;
}
