package com.slowmedia0.PP1.DTO;


import com.slowmedia0.PP1.entity.BaseEntity;
import com.slowmedia0.PP1.entity.Wallet;
import com.slowmedia0.PP1.entity.WalletHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigInteger;

@Mapper(componentModel = "spring")
public interface WalletHistoryMapper {

    @Mapping(target = "walletId", source = "walletHistory.wallet.id")
    WalletHistoryDTO toDto (WalletHistory walletHistory);
    @Mapping(target = "wallet", expression = "java(IdToWallet(walletHistoryDTO.walletId()))")
    WalletHistory toEntity (WalletHistoryDTO walletHistoryDTO);

    default Wallet IdToWallet (BigInteger id){
        if (id==null) return null;
        Wallet wallet = new Wallet();
        wallet.setId(id);
        return wallet;
    }
}
