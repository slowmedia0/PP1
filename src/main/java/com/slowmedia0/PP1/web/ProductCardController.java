package com.slowmedia0.PP1.web;

import com.slowmedia0.PP1.DTO.ProductDTO;
import com.slowmedia0.PP1.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@Controller
@RequestMapping("/home/productCard")
public class ProductCardController {

    private final ProductService productService;

    public ProductCardController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(params = "goToProductCard")
    public String goToProductCard(@RequestParam("product_id") BigInteger id, Model model){
        ProductDTO productDTO = productService.getProduct(id);
        model.addAttribute("productDTO", productDTO);
        return "productCard";
    }
}
