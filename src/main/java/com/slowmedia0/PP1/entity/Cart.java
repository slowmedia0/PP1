package com.slowmedia0.PP1.entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table(name = "carts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Cart extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "carts_id_seq")
    @SequenceGenerator(
            name = "carts_id_seq_gen",
            sequenceName = "carts_id_seq",
            allocationSize = 50
    )
    private BigInteger id;

    //Связи
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartElem> cartElems;

     @Transactional
     public void addCartElem(CartElem cartElem){
        cartElems.add(cartElem);
        cartElem.setCart(this);
    }
}
