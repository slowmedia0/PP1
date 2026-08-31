package com.slowmedia0.PP1.services;

import com.slowmedia0.PP1.DTO.ProductDTO;
import com.slowmedia0.PP1.DTO.ProductMapper;
import com.slowmedia0.PP1.database.ProductRepository;
import com.slowmedia0.PP1.entity.Product;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class ProductService {

    ProductRepository productRepository;
    ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Transactional
    public Page<ProductDTO> showPageOfProducts(@PositiveOrZero(message = "PageNumber should be positive or zero!") int pageNumber, @Positive(message = "Size of Page should be positive!") int size){
        Pageable pageRequest = PageRequest.of(pageNumber,size, Sort.by("id").ascending());
        Page<Product> page = productRepository.findAll(pageRequest);
        Page<ProductDTO> pageDto = page.map(element -> productMapper.toDto(element));
        return pageDto;
    }

    @Transactional
    public Optional<ProductDTO> getProductDTOById(@NotNull(message = "Product's Id can not be null!") @Positive(message = "Product's Id should be positive!") BigInteger productId){
        Optional<ProductDTO> productDTO = productRepository.findById(productId).map(elem -> productMapper.toDto(elem));
        return  productDTO;
    }

}
