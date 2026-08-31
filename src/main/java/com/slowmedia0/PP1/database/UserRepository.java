package com.slowmedia0.PP1.database;

import com.slowmedia0.PP1.entity.User;
import org.mapstruct.control.MappingControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.math.BigInteger;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, BigInteger> {
    Optional<User> findByLogin (String login);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.cart WHERE u.login = :login")
    Optional<User> findByLoginWithCart(@Param("login") String login);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.wallet WHERE u.login = :login")
    Optional<User> findByLoginWithWallet(@Param("login") String login);
}
