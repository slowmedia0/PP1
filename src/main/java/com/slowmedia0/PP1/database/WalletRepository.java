package com.slowmedia0.PP1.database;

import com.slowmedia0.PP1.entity.User;
import com.slowmedia0.PP1.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.math.BigInteger;
import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, BigInteger> {
    @Query("SELECT w FROM Wallet w LEFT JOIN FETCH w.walletHistory WHERE w.id = :id")
    Optional<Wallet> findByIdWithWalletHistory(@Param("id") BigInteger id);
}
