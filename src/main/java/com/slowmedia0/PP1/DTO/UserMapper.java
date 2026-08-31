package com.slowmedia0.PP1.DTO;


import com.slowmedia0.PP1.entity.User;
import com.slowmedia0.PP1.entity.Cart;
import com.slowmedia0.PP1.entity.Wallet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigInteger;

@Mapper(componentModel = "spring")
public interface UserMapper {


    @Mapping(target = "cartId", source = "user.cart.id")
    @Mapping(target = "orderDTOS", ignore = true)
    @Mapping(target = "walletId", source = "user.wallet.id")
    UserDTO toDto (User user);

    @Mapping(target = "cart", expression = "java(idToCart(userDTO.cartId()))")
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "wallet", expression = "java(idToWallet(userDTO.walletId()))")
    User toEntity (UserDTO userDTO);

    default Cart idToCart (BigInteger id){
        if (id==null) return null;
        Cart cart = new Cart();
        cart.setId(id);
        return cart;
    }

    default Wallet idToWallet (BigInteger id){
        if (id==null) return null;
        Wallet wallet = new Wallet();
        wallet.setId(id);
        return wallet;
    }
}
