package com.slowmedia0.PP1.web;

import com.slowmedia0.PP1.services.ProductService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/home", produces = "application/json")
@CrossOrigin(origins = {"http://localhost:9090","http://pp1.com:9090"})
public class HomeRestController {
    private final ProductService productService;

    public HomeRestController(ProductService productService) {
        this.productService = productService;
    }

}
