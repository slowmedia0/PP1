package com.slowmedia0.PP1.services;


import com.slowmedia0.PP1.DTO.ActionEntity;
import com.slowmedia0.PP1.DTO.CartElemDTO;
import com.slowmedia0.PP1.DTO.CartElemMapper;
import com.slowmedia0.PP1.DTO.CartMapper;
import com.slowmedia0.PP1.database.CartElemRepository;
import com.slowmedia0.PP1.database.CartRepository;
import com.slowmedia0.PP1.database.ProductRepository;
import com.slowmedia0.PP1.entity.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@SessionAttributes("cart")
public class CartService {
    private final CartRepository cartRepository;
    private final CartElemRepository cartElemRepository;
    private final ProductRepository productRepository ;
    private final UserService userService;
    private final CartElemMapper cartElemMapper;

    public CartService(CartRepository cartRepository, CartElemRepository cartElemRepository, ProductRepository productRepository, UserService userService, CartElemMapper cartElemMapper) {
        this.cartRepository = cartRepository;
        this.cartElemRepository = cartElemRepository;
        this.productRepository = productRepository;
        this.userService = userService;
        this.cartElemMapper = cartElemMapper;
    }

    @Transactional
    public Optional<CartElemDTO> plusProductsInCartElem(@NotNull @Positive BigInteger cartElemId, @Positive int count){
        Optional<CartElem> cartElemOp = cartElemRepository.findById(cartElemId);
        if (cartElemOp.isPresent()) {
            CartElem cartElem = cartElemOp.get();
            cartElem.setCount(cartElem.getCount()+count);
            CartElemDTO cartElemDTO = cartElemMapper.toDto(cartElemRepository.saveAndFlush(cartElem));
            cartElemDTO.setLastAction(ActionEntity.UPDATED);
            return Optional.of(cartElemDTO);
        }
        else {
            return Optional.empty();
        }
    }

    @Transactional
    public Optional<CartElemDTO> substructProductsInCartElem(@NotNull @Positive BigInteger cartElemId, @Positive int count) {
        Optional<CartElem> cartElemOp = cartElemRepository.findById(cartElemId);
        CartElemDTO cartElemDTO;
        if (cartElemOp.isPresent()) {
            CartElem cartElem = cartElemOp.get();
            if (cartElem.getCount()-count==0){
                cartElemDTO = new CartElemDTO();
                cartElemDTO.setLastAction(ActionEntity.DELETED);
                return Optional.of(cartElemDTO);
            }
            else if (cartElem.getCount()-count<0) {
                throw new IllegalArgumentException("Final count of products is negative!");
            }
            else {
                cartElem.setCount(cartElem.getCount()-count);
                cartElemDTO = cartElemMapper.toDto(cartElemRepository.saveAndFlush(cartElem));
                cartElemDTO.setLastAction(ActionEntity.UPDATED);
                return Optional.of(cartElemDTO);
            }
        }
        else {
            return Optional.empty();
        }
    }


    @Transactional(readOnly = true)
    public List<CartElem> refreshSumPriceAndCount(List<BigInteger> cartElemIds){
        if (cartElemIds==null) return Collections.emptyList();
        return cartElemRepository.findAllById(cartElemIds);
    }


    @Transactional
    public Optional<CartElemDTO> addProductToCart(@NotNull @Positive BigInteger productId){
        Cart cart = getCurrentCartWithCartElems();
        List<CartElem> cartElems = cart.getCartElems();
        Optional<Product> product = productRepository.findById(productId);
        CartElemDTO cartElemDTO;
        if (product.isPresent()) {
            for (CartElem cartElem : cartElems) {
                Product productOld = cartElem.getProduct();
                if (product.get().getArticleNumber().equals(productOld.getArticleNumber())) {
                    cartElem.setCount(cartElem.getCount() + 1);
                    cartElemDTO = cartElemMapper.toDto(cartElemRepository.saveAndFlush(cartElem));
                    cartElemDTO.setLastAction(ActionEntity.UPDATED);
                    return Optional.of(cartElemDTO);
                }
            }
            CartElem cartElemNew = new CartElem();
            cartElemNew.setProduct(product.get());
            cartElemNew.setCart(cart);
            cartElemNew.setCount(1);
            cartElemNew = cartElemRepository.saveAndFlush(cartElemNew);
            cart.addCartElem(cartElemNew);
            cartElemDTO = cartElemMapper.toDto(cartElemNew);
            cartElemDTO.setLastAction(ActionEntity.CREATED);
            return Optional.of(cartElemDTO);
        }
        else {
            return Optional.empty();
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

    public Cart getCurrentCartWithoutCartElems(){
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

    public Cart getCurrentCartWithCartElems(){
        User user = userService.getCurrentUserWithCart();
        Cart cart;
        if (user.getCart()==null) {
            cart = processAndGetCartByUser(user);
        }
        else {
            cart = cartRepository.findByIdWithCartElems(user.getCart().getId()).orElseGet(() -> processAndGetCartByUser(user));
        }
        return cart;
    }
}
