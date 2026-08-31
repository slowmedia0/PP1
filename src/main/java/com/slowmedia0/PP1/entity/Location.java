package com.slowmedia0.PP1.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.rest.core.annotation.RestResource;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "locations")
public class Location extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "locations_id_seq_gen")
    @SequenceGenerator(
            name = "locations_id_seq_gen",
            sequenceName = "locations_id_seq",
            allocationSize = 50
    )
    private BigInteger id;

    @Column(name = "position_latitude")
    private BigDecimal positionLatitude;
    @Column(name = "position_longitude")
    private BigDecimal positionLongitude;
    @Column(name = "address")
    private String address;

    //Связи
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    private void addOrder (Order order){
        orders.add(order);
        order.setLocation(this);
    }
}
