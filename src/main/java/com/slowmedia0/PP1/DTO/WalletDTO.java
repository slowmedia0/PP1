package com.slowmedia0.PP1.DTO;

import com.slowmedia0.PP1.entity.User;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;

import java.math.BigInteger;

public record WalletDTO(
        BigInteger id,
        UserDTO userDTO,
        BigInteger balance,
        WalletHistoryDTO walletHistoryDTO
) {
}