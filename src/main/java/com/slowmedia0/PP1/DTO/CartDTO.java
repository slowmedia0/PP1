package com.slowmedia0.PP1.DTO;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public record CartDTO(
        BigInteger id,
        List<CartElemDTO> cartElemDTOS,
        UserDTO userDTO
) {
    private BigInteger countSumPriceInKopeks(){
        BigInteger sumPrice=BigInteger.ZERO;
        for (CartElemDTO cartElemDTO: cartElemDTOS){
            sumPrice = sumPrice.add(cartElemDTO.getProductDTO().price().multiply(BigInteger.valueOf(cartElemDTO.getCount())));
        }
        return sumPrice;
    }

    public BigDecimal getSumPriceInRubles() {
        return new BigDecimal(countSumPriceInKopeks()).divide(new BigDecimal(100));
    }
}
