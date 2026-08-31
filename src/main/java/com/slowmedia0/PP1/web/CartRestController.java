package com.slowmedia0.PP1.web;

import com.slowmedia0.PP1.DTO.ActionEntity;
import com.slowmedia0.PP1.DTO.CartElemDTO;
import com.slowmedia0.PP1.DTO.CartMapper;
import com.slowmedia0.PP1.entity.Cart;
import com.slowmedia0.PP1.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Optional;
import java.util.logging.ErrorManager;

@RestController
@RequestMapping("/api/user/cart")
@CrossOrigin(origins = {"http://localhost:9090","http://pp1.com:9090"})
public class CartRestController {
    private final CartService cartService;
    private final CartMapper cartMapper;

    public CartRestController(CartService cartService, CartMapper cartMapper) {
        this.cartService = cartService;
        this.cartMapper = cartMapper;
    }

    @PostMapping("/add-product")
    public ResponseEntity<CartElemDTO> addProductToCart(@RequestParam(name = "productId") BigInteger productId){
        java.util.Optional<CartElemDTO> cartElemDTO = cartService.addProductToCart(productId);
        if (cartElemDTO.isEmpty()) return ResponseEntity.notFound().build();
        CartElemDTO dto = cartElemDTO.get();
        if (dto.getLastAction().equals(ActionEntity.CREATED)){
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        }
        return ResponseEntity.ok(dto);
    }

    @PatchMapping("/cartElem/plus-count")
    public ResponseEntity<CartElemDTO> plusProductsInCartElem(@RequestParam(name = "cartElemId") BigInteger cartElemId,@RequestParam(name = "count") int count){
        Optional<CartElemDTO> cartElemDTO = cartService.plusProductsInCartElem(cartElemId,count);
        if (cartElemDTO.isEmpty()) return ResponseEntity.notFound().build();
        CartElemDTO dto = cartElemDTO.get();
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PatchMapping("/cartElem/substruct-count")
    public ResponseEntity<CartElemDTO> substructProductsInCartElem(@RequestParam(name = "cartElemId") BigInteger cartElemId,@RequestParam(name = "count") int count){
            Optional<CartElemDTO> cartElemDTO = cartService.substructProductsInCartElem(cartElemId, count);
            if (cartElemDTO.isEmpty()) return ResponseEntity.notFound().build();
            CartElemDTO dto = cartElemDTO.get();
            if (dto.getLastAction().equals(ActionEntity.DELETED)){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorMessage> handleException(IllegalArgumentException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(exception.getMessage(),HttpStatus.BAD_REQUEST));
    }
}
