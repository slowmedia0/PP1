package com.slowmedia0.PP1.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.rest.core.annotation.RestResource;


import java.math.BigInteger;

@Table(name = "cart_elems")
@Getter
@Setter
@NoArgsConstructor
@Entity

public class CartElem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_elems_id_seq_gen")
    @SequenceGenerator(
            name = "cart_elems_id_seq_gen",
            sequenceName = "cart_elems_id_seq",
            allocationSize = 50
    )
    private BigInteger id;
    @Column(name = "count")
    private int count;

    //Связи
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}

