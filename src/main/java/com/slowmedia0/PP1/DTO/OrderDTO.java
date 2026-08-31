package com.slowmedia0.PP1.DTO;

import com.slowmedia0.PP1.entity.OrderElem;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public record OrderDTO(
        BigInteger id,
        BigInteger promocodeNumber,
        UserDTO userDTO,
        //LocationDTO locationDTO
        List<OrderELemDTO> orderELemsDTOS

) {
    private BigInteger countSumPriceInKopeks(){
        BigInteger sumPrice=BigInteger.ZERO;
        for (OrderELemDTO orderELemDTO: orderELemsDTOS){
            sumPrice = sumPrice.add(orderELemDTO.priceOfProduct().multiply(BigInteger.valueOf(orderELemDTO.count())));
        }
        return sumPrice;
    }

    public BigDecimal getSumPriceInRubles() {
        return new BigDecimal(countSumPriceInKopeks()).divide(new BigDecimal(100));
    }
}
