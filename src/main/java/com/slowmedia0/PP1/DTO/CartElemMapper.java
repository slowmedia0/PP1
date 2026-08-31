package com.slowmedia0.PP1.DTO;


import com.slowmedia0.PP1.entity.Cart;
import com.slowmedia0.PP1.entity.CartElem;
import com.slowmedia0.PP1.entity.OrderElem;
import com.slowmedia0.PP1.entity.Product;
import jakarta.persistence.Id;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.convert.Jsr310Converters;

import javax.swing.text.html.parser.Entity;
import java.math.BigInteger;

@Mapper(componentModel = "spring",uses = {ProductMapper.class})
public interface CartElemMapper {

    @Mapping(target = "cartId", source = "product.id")
    @Mapping(target = "productDTO",source = "product")
    @Mapping(target = "checked", ignore = true)
    CartElemDTO toDto (CartElem cartElem);

    @Mapping(target = "cart", expression = "java(idToCart(cartElemDTO.getCartId()))")
    @Mapping(target = "product", source = "productDTO")
    CartElem toEntity (CartElemDTO cartElemDTO);
    default Cart idToCart (BigInteger id){
        if (id==null) return null;
        Cart cart = new Cart();
        cart.setId(id);
        return cart;
    }


    @Mapping(target = "priceOfProduct", source = "product.price")
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "id", ignore = true)
    OrderElem toOrderElem (CartElem cartElem);
}
