package com.slowmedia0.PP1.database;

import com.slowmedia0.PP1.entity.CartElem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface CartElemRepository extends JpaRepository<CartElem, BigInteger> {
    List<CartElem> findByCartId(BigInteger cartId);
}