package com.slowmedia0.PP1.database;

import com.slowmedia0.PP1.entity.OrderElem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import java.math.BigInteger;

public interface OrderElemRepository extends JpaRepository<OrderElem, BigInteger> {
}
