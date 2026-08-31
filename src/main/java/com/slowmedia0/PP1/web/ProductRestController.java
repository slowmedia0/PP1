package com.slowmedia0.PP1.web;

import com.slowmedia0.PP1.DTO.ProductDTO;
import com.slowmedia0.PP1.services.ProductService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Optional;

@RestController
@RequestMapping("/api/home/product")
@CrossOrigin(origins = {"http://localhost:9090","http://pp1.com:9090"})
public class ProductRestController {
    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable BigInteger id){
        Optional<ProductDTO> productDTO = productService.getProductDTOById(id);
        if (productDTO.isPresent()){
            return ResponseEntity.ok(productDTO.get());
        }
        return ResponseEntity.notFound().build();
    }
}
