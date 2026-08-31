package com.slowmedia0.PP1.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="products")
@Entity
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy =GenerationType.SEQUENCE, generator = "products_id_seq_gen")
    @SequenceGenerator(
            name = "products_id_seq_gen",
            sequenceName = "products_id_seq",
            allocationSize = 50
    )
    private BigInteger id;
    @Column(name = "article_number")
    private BigInteger articleNumber;
    @Column(name = "price")
    private BigInteger price;
    @Column(name = "description_small")
    private String descriptionSmall;
    @Column(name = "description_big")
    private String descriptionBig;
    @Column(name = "inventory")
    private int inventory;


    @Column(name = "product_photo_directory_url")
    private String productPhotoDirectoryURL;
    //private Map<String,Object> attributes;

    //Связи
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "owner_id")
    private User user;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartElem>cartElems = new ArrayList<>();

    private void addCartElem(CartElem cartElem){
        cartElems.add(cartElem);
        cartElem.setProduct(this);
    }

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductCategoryTOProduct> productCategoryTOProducts;

    private void addProductCategoryTOProduct(ProductCategoryTOProduct productCategoryTOProduct){
        productCategoryTOProducts.add(productCategoryTOProduct);
        productCategoryTOProduct.setProduct(this);
    }

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderElem> orderElems = new ArrayList<>();

    private void addOrderElem (OrderElem orderElem){
        orderElems.add(orderElem);
        orderElem.setProduct(this);
    }


    public Product(BigInteger id, BigInteger articleNumber, BigInteger price, String descriptionSmall, String descriptionBig, int inventory) {
        this.id = id;
        this.articleNumber = articleNumber;
        this.price = price;
        this.descriptionSmall = descriptionSmall;
        this.descriptionBig = descriptionBig;
        this.inventory = inventory;
    }

}

