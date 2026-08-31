package com.slowmedia0.PP1.DTO;

import com.slowmedia0.PP1.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {OrderElemsMapper.class, UserMapper.class})
public interface OrderMapper {

    @Mapping(target = "userDTO", source = "user")
    @Mapping(target = "orderELemsDTOS", source = "orderElems")
    OrderDTO toDto(Order order);
    @Mapping(target = "location", ignore = true)
    @Mapping(source = "userDTO", target = "user")
    @Mapping(source = "orderELemsDTOS", target = "orderElems")
    Order toEntity(OrderDTO orderDTO);
}
