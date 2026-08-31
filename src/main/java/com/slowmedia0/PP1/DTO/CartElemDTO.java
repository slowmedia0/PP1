package com.slowmedia0.PP1.DTO;

import com.slowmedia0.PP1.entity.Cart;
import com.slowmedia0.PP1.entity.Product;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;

@Data
@NoArgsConstructor
public class CartElemDTO {
    private BigInteger id;
    private int count;
    private BigInteger cartId;
    private ProductDTO productDTO;

    private boolean checked;
}
