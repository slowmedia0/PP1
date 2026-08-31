package com.slowmedia0.PP1.web;

import com.slowmedia0.PP1.DTO.ProductDTO;
import com.slowmedia0.PP1.DTO.ProductMapper;
import com.slowmedia0.PP1.database.ProductRepository;
import com.slowmedia0.PP1.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import com.slowmedia0.PP1.entity.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/home")
@SessionAttributes("product")
public class HomeController {

    private final ProductService productService;

    @Autowired
    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(name = "goToHome")
    public String addProductToModel(Model model){
        List<ProductDTO> productDTOS = productService.showAllProductsFromDb();
        model.addAttribute("productDTOS",productDTOS);
        return "home";
    }

}
