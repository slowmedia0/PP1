package com.slowmedia0.PP1.DTO;

import com.slowmedia0.PP1.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class, CartElemMapper.class})
public interface CartMapper {

    @Mapping(target = "cartElemDTOS", source = "cartElems")
    @Mapping(target = "userDTO",source = "user")
    CartDTO toDto (Cart cart);

    @Mapping(target = "cartElems", ignore = true)
    @Mapping(target = "user", source = "userDTO")
    Cart toEntity (CartDTO cartDTO);
}
