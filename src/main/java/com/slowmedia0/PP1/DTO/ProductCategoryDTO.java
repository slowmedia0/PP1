package com.slowmedia0.PP1.DTO;

import com.slowmedia0.PP1.entity.ProductCategoryTOProduct;

import java.math.BigInteger;
import java.util.List;

public record ProductCategoryDTO(
        BigInteger id,
        String title,
        BigInteger parentId,
        List<ProductCategoryDTO> childrenProductCategoryDTO
) {
}
