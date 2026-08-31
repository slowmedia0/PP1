package com.slowmedia0.PP1.database;

import com.slowmedia0.PP1.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;


public interface ProductRepository extends JpaRepository<Product, BigInteger> {
}

