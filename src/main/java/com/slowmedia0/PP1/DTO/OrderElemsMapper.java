package com.slowmedia0.PP1.DTO;

import com.slowmedia0.PP1.entity.Order;
import com.slowmedia0.PP1.entity.OrderElem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigInteger;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface OrderElemsMapper {

    @Mapping(target = "orderId", source = "order.id")
    @Mapping(target = "productDTO", source = "product")
    OrderELemDTO toDto (OrderElem orderElem);

    @Mapping(target = "order", expression = "java(convertIdToOrder(orderELemDTO.orderId()))")
    @Mapping(target = "product", source = "productDTO")
    OrderElem toEntity (OrderELemDTO orderELemDTO);

    default Order convertIdToOrder(BigInteger id){
        if (id==null) return null;
        Order order = new Order();
        order.setId(id);
        return order;
    }
}
