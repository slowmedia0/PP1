package com.slowmedia0.PP1.DTO;

import com.slowmedia0.PP1.entity.Sex;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.type.PostgreSQLEnumJdbcType;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

public record UserDTO(
     BigInteger id,
     String login,
     String mail,
     String password,
     LocalDate dateOfBirth,
     Sex sex,
     String phoneNumber,
     String userImageURL,
     BigInteger cartId,
     List<OrderDTO> orderDTOS,
     BigInteger walletId
){
}
