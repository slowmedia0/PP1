package com.slowmedia0.PP1.web;

import com.slowmedia0.PP1.DTO.ProductDTO;
import com.slowmedia0.PP1.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/home")
@CrossOrigin(origins = {"http://localhost:9090","http://pp1.com:9090"})
public class HomeRestController {
    private final ProductService productService;

    public HomeRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/show-page-of-products", produces = "application/json")
    public ResponseEntity<Page<ProductDTO>> showPartOfProducts(@RequestParam(name = "page") int page, @RequestParam(name = "size") int size){
        Page<ProductDTO> pageDto = productService.showPageOfProducts(page,size);
        return ResponseEntity.ok(pageDto);
    }
}
