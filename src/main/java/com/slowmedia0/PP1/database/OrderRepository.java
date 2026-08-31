package com.slowmedia0.PP1.database;

import com.slowmedia0.PP1.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface OrderRepository extends JpaRepository<Order, BigInteger> {
}
