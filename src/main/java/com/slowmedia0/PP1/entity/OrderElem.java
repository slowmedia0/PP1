package com.slowmedia0.PP1.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.rest.core.annotation.RestResource;

import java.math.BigInteger;
@Entity
@Table(name = "order_elems")
@Getter
@Setter
@NoArgsConstructor

public class OrderElem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_elems_id_seq_gen")
    @SequenceGenerator(
            name = "order_elems_id_seq_gen",
            sequenceName = "order_elems_id_seq",
            allocationSize = 50
    )
    private BigInteger id;

    @Column(name = "price_of_product")
    private BigInteger priceOfProduct;
    @Column(name = "count")
    private int count;

    //Связи
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
    
}
