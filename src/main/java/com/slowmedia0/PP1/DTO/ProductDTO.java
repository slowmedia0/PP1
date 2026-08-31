package com.slowmedia0.PP1.DTO;

import com.slowmedia0.PP1.entity.*;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public record ProductDTO(
        BigInteger id,
        BigInteger articleNumber,
        BigInteger price,
        String descriptionSmall,
        String descriptionBig,
        int inventory,
        String productPhotoDirectoryURL,
        // Map<String,Object> attributes;
        BigInteger ownerId,
        List<ProductCategoryDTO> productCategoryDTOS,

        ActionEntity action

) {
    public BigDecimal getPriceInRubles() {
        return new BigDecimal(this.price).divide(new BigDecimal(100));
    }
}
