package com.slowmedia0.PP1.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "product_categories_to_products")
public class ProductCategoryTOProduct extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_categories_to_products_id_seq_gen")
    @SequenceGenerator(
            name = "product_categories_to_products_id_seq_gen",
            sequenceName = "product_categories_to_products_id_seq",
            allocationSize = 50
    )
    private BigInteger id;

    //Связи
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_category_id")
    private ProductCategory productCategory;
}
