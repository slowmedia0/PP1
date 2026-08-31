package com.slowmedia0.PP1.database;

import com.slowmedia0.PP1.entity.CartElem;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.math.BigInteger;
import java.util.List;

public interface CartElemRepository extends JpaRepository<CartElem, BigInteger> {
    List<CartElem> findByCartId(BigInteger cartId);
}