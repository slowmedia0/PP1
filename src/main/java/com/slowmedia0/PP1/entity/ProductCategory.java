package com.slowmedia0.PP1.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_categories")
public class ProductCategory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_categories_id_seq_gen")
    @SequenceGenerator(
            name = "product_categories_id_seq_gen",
            sequenceName = "product_categories_id_seq",
            allocationSize = 50
    )
    private BigInteger id;

    @Column(name = "title")
    private String title;

    //Связи
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_product_category_id")
    private ProductCategory parentProductCategory;
    @OneToMany(mappedBy = "parentProductCategory",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductCategory> childrenProductCategories = new ArrayList<>();

    private void addChildProductCategory (ProductCategory childProductCategory){
        childrenProductCategories.add(childProductCategory);
        childProductCategory.setParentProductCategory(this);
    }

    @OneToMany(mappedBy = "productCategory",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductCategoryTOProduct> productCategoryTOProducts = new ArrayList<>();

    private void addProductCategoryTOProduct (ProductCategoryTOProduct productCategoryTOProduct){
        productCategoryTOProducts.add(productCategoryTOProduct);
        productCategoryTOProduct.setProductCategory(this);
    }

}
