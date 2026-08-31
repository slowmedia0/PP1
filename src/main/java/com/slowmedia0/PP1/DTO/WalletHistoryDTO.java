package com.slowmedia0.PP1.DTO;

import com.slowmedia0.PP1.entity.WalletTransactionStatus;
import com.slowmedia0.PP1.entity.WalletTransactionType;

import java.math.BigInteger;
import java.time.LocalDateTime;

public record WalletHistoryDTO(
        BigInteger id,
        WalletTransactionType walletTransactionType,
        WalletTransactionStatus walletTransactionStatus,
        BigInteger sumPriceOfOperation,
        LocalDateTime timeOfOperation,
        BigInteger walletId
){
}
