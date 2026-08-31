package com.slowmedia0.PP1.DTO;

import com.slowmedia0.PP1.entity.Wallet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import javax.swing.text.html.parser.Entity;

@Mapper(componentModel = "spring", uses = {UserMapper.class, WalletHistoryMapper.class})
public interface WalletMapper {
    @Mapping(target = "userDTO", source = "user")
    @Mapping(target = "walletHistoryDTO", source = "walletHistory")
    WalletDTO toDto (Wallet wallet);
    @Mapping(target = "user", source = "userDTO")
    @Mapping(target = "walletHistory", source = "walletHistoryDTO")
    Wallet toEntity (WalletDTO walletDTO);
}
