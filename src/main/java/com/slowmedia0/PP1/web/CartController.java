
package com.slowmedia0.PP1.web;


import com.slowmedia0.PP1.DTO.*;
import com.slowmedia0.PP1.database.CartElemRepository;
import com.slowmedia0.PP1.database.CartRepository;
import com.slowmedia0.PP1.database.ProductRepository;
import com.slowmedia0.PP1.entity.CartElem;
import com.slowmedia0.PP1.entity.User;
import com.slowmedia0.PP1.services.CartService;
import com.slowmedia0.PP1.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import com.slowmedia0.PP1.entity.Cart;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    private final CartMapper cartMapper;

    public CartController(CartService cartService, CartMapper cartMapper) {
        this.cartService = cartService;
        this.cartMapper = cartMapper;
    }



    @PostMapping(params = "addСartToOrder")
    public String addCartToOrder(@RequestParam(value = "cartElemIds", required = false) List<BigInteger> cartElemIdS, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("cartElemIds", cartElemIdS);
        return "redirect:/cart/order";
    }

    @PostMapping
     public String refreshSumPriceAndCount(@RequestParam(value = "cartElemIds", required = false) List<BigInteger> cartElemIdS, RedirectAttributes redirectAttributes){
        List<CartElem> cartElems = cartService.refreshSumPriceAndCount(cartElemIdS);
        BigInteger sumPriceInKopeks = BigInteger.ZERO;
        if (!cartElems.isEmpty()){
            for (CartElem cartElem : cartElems){
                sumPriceInKopeks=sumPriceInKopeks.add(cartElem.getProduct().getPrice());
            }
        }
        redirectAttributes.addFlashAttribute("selectedCount", cartElems.size());
        redirectAttributes.addFlashAttribute("sumSelectedPriceInRubles", getPriceInRubles(sumPriceInKopeks));
        List<BigInteger> selectedCartElemIds = cartElemIdS;
        if (cartElems.isEmpty()){
            selectedCartElemIds=Collections.emptyList();
        }
        redirectAttributes.addFlashAttribute("selectedCartElemIds",selectedCartElemIds);
        return "redirect:/cart";
    }


    @GetMapping
    public String goToCart(Model model) {
        Cart cart = cartService.getCurrentCartWithoutCartElems();

        List<BigInteger> selectedCartElemIds = (List<BigInteger>) model.getAttribute("selectedCartElemIds");
        CartDTO cartDTO = cartMapper.toDto(cart);

        if (selectedCartElemIds==null){
            for (CartElemDTO cartElemDTO : cartDTO.cartElemDTOS()){
                cartElemDTO.setChecked(true);
            }
            if (!model.containsAttribute("sumSelectedPriceInRubles")) {
                model.addAttribute("sumSelectedPriceInRubles", cartDTO.getSumPriceInRubles());
            }
            if (!model.containsAttribute("selectedCount")) {
                model.addAttribute("selectedCount", cartDTO.cartElemDTOS().size());
            }
        }
        else if  (!selectedCartElemIds.isEmpty()){
            for (CartElemDTO cartElemDTO : cartDTO.cartElemDTOS()){
                if (selectedCartElemIds.contains(cartElemDTO.getId())){
                    cartElemDTO.setChecked(true);
                }
                else {
                    cartElemDTO.setChecked(false);
                }
            }
        }
        else {
            for (CartElemDTO cartElemDTO : cartDTO.cartElemDTOS()){
                cartElemDTO.setChecked(false);
            }
        }
        model.addAttribute("cartDTO", cartDTO);

        return "cart";
    }


    public BigDecimal getPriceInRubles(BigInteger priceInKopeks) {
        return new BigDecimal(priceInKopeks).divide(new BigDecimal(100));
    }
}
