package com.saga.order.infra.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
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
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    private OrderEntity order;
    private BigDecimal price;
    @OneToMany(mappedBy = "suborder", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SuborderItemEntity> suborderItems = new HashSet<>();

    public void addSuborderItem(SuborderItemEntity suborderItem) {
        suborderItem.setSuborder(this);
        this.suborderItems.add(suborderItem);
    }
}
