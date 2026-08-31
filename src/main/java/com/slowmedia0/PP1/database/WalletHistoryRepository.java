package com.slowmedia0.PP1.database;

import com.slowmedia0.PP1.entity.WalletHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import java.math.BigInteger;

public interface WalletHistoryRepository extends JpaRepository<WalletHistory, BigInteger> {
}
