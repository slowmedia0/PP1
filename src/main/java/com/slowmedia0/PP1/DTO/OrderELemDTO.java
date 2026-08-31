package com.slowmedia0.PP1.DTO;

import java.math.BigInteger;

public record OrderELemDTO(
        BigInteger id,
        BigInteger priceOfProduct,
        int count,
        BigInteger orderId,
        ProductDTO productDTO
) {
}
