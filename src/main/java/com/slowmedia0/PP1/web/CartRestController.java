package com.slowmedia0.PP1.web;

import com.slowmedia0.PP1.DTO.CartMapper;
import com.slowmedia0.PP1.entity.Cart;
import com.slowmedia0.PP1.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping(path = "/api/carts", produces = "application/json")
@CrossOrigin(origins = {"http://localhost:9090","http://pp1.com:9090"})
public class CartRestController {
    private final CartService cartService;
    private final CartMapper cartMapper;

    public CartRestController(CartService cartService, CartMapper cartMapper) {
        this.cartService = cartService;
        this.cartMapper = cartMapper;
    }
}
