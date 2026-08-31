package com.slowmedia0.PP1.services;


import com.slowmedia0.PP1.database.CartElemRepository;
import com.slowmedia0.PP1.database.CartRepository;
import com.slowmedia0.PP1.database.ProductRepository;
import com.slowmedia0.PP1.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@SessionAttributes("cart")
public class CartService {
    private final CartRepository cartRepository;
    private final CartElemRepository cartElemRepository;
    private final ProductRepository productRepository ;
    private final UserService userService;

    public CartService(CartRepository cartRepository, CartElemRepository cartElemRepository, ProductRepository productRepository, UserService userService) {
        this.cartRepository = cartRepository;
        this.cartElemRepository = cartElemRepository;
        this.productRepository = productRepository;
        this.userService = userService;
    }

    @Transactional
    public void addProductByCartElemIdToCart(BigInteger cartElemId){
        CartElem cartElem = cartElemRepository.findById(cartElemId).get();
        int count = cartElem.getCount();
        count++;
        cartElem.setCount(count);
    }

    @Transactional
    public void deleteProductFromCart(BigInteger cartElemId) {
        CartElem cartElem = cartElemRepository.findById(cartElemId).get();
        int count = cartElem.getCount();
        count--;
        if (count == 0) {
            cartElemRepository.deleteById(cartElemId);
        } else {
            cartElem.setCount(count);
        }
    }


    @Transactional(readOnly = true)
    public List<CartElem> refreshSumPriceAndCount(List<BigInteger> cartElemIds){
        if (cartElemIds==null) return Collections.emptyList();
        return cartElemRepository.findAllById(cartElemIds);
    }


    @Transactional
    public void addProductToCart(BigInteger productId, Cart cart){
        List<CartElem> cartElems = cartElemRepository.findByCartId(cart.getId());
        Product product = productRepository.findById(productId).get();
        boolean existsCartElem=false;
        for (CartElem cartElem : cartElems) {
            Product productOld = cartElem.getProduct();
            if (product.getArticleNumber().equals(productOld.getArticleNumber())){
                cartElem.setCount(cartElem.getCount()+1);
                existsCartElem=true;
                break;
            }
        }
        if (!existsCartElem){
            CartElem cartElemNew = new CartElem();
            cartElemNew.setProduct(product);
            cartElemNew.setCart(cart);
            cartElemNew.setCount(1);
            cartElemRepository.save(cartElemNew);
            cart.addCartElem(cartElemNew);
        }
    }

    public Cart defaultProcessCart(User user){
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setCartElems(new ArrayList<>());
        return cart;
    }

    @Transactional
    public Cart processAndGetCartByUser(User user) {
        Cart cart = defaultProcessCart(user);
        return cartRepository.save(cart);
    }

    public Cart getCurrentCart(){
        User user = userService.getCurrentUserWithCart();
        Cart cart;
        if (user.getCart()==null) {
            cart = processAndGetCartByUser(user);
        }
        else {
            cart=user.getCart();
        }
        return cart;
    }
}
