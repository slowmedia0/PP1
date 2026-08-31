package com.slowmedia0.PP1.database;

import com.slowmedia0.PP1.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.crypto.spec.OAEPParameterSpec;
import java.math.BigInteger;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, BigInteger> {
    @Query("SELECT c FROM Cart c JOIN FETCH c.cartElems WHERE c.id = :id")
    Optional<Cart> findByIdWithCartElems(@Param("id") BigInteger id);
}
