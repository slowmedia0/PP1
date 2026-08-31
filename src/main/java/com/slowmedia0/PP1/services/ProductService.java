package com.slowmedia0.PP1.services;

import com.slowmedia0.PP1.DTO.ProductDTO;
import com.slowmedia0.PP1.DTO.ProductMapper;
import com.slowmedia0.PP1.database.ProductRepository;
import com.slowmedia0.PP1.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class ProductService {

    ProductRepository productRepository;
    ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<ProductDTO> showAllProductsFromDb(){
        List<Product> products = productRepository.findAll();
        List<ProductDTO>productDTOS = products.stream().map(element -> productMapper.toDto(element)).toList();
        return productDTOS;
    }

    @Transactional
    public ProductDTO getProduct(BigInteger productId){
        Product product = productRepository.findById(productId).get();
        ProductDTO productDTO = productMapper.toDto(product);
        return  productDTO;
    }

}
