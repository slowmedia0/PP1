package com.slowmedia0.PP1.web;

import lombok.Data;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;

@Controller
@Data
@RequestMapping("/home/cart/orders/promocodes")
public class PromocodesController {
    @PostMapping(name = "goToPromocodes")
    public String goToPromocodes(@RequestParam("cart_id")BigInteger cartId, Model model){
        model.addAttribute("cart_id", cartId);
        return "promocodes";
    }
}
