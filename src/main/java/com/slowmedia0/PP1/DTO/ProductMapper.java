package com.slowmedia0.PP1.DTO;

import ch.qos.logback.core.read.ListAppender;
import com.slowmedia0.PP1.entity.Product;
import com.slowmedia0.PP1.entity.ProductCategory;
import com.slowmedia0.PP1.entity.ProductCategoryTOProduct;
import com.slowmedia0.PP1.entity.User;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.aspectj.asm.IElementHandleProvider;
import org.attoparser.IElementHandler;
import org.hibernate.metamodel.model.domain.MapPersistentAttribute;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.jpa.repository.EntityGraph;
import org.thymeleaf.engine.IElementDefinitionsAware;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Mapper(componentModel = "spring", uses =  {UserMapper.class, ProductCategoryMapper.class })
public interface ProductMapper {

    @Mapping(target = "ownerDTO",source = "user")
    @Mapping(target = "productCategoryDTOS", source = "productCategoryTOProducts")
    ProductDTO toDto (Product product);
    default List<ProductCategoryDTO> mapListProductCategoriesTO (List<ProductCategoryTOProduct> list){
        if (list == null) return null;
        ProductCategoryMapper productCategoryMapper = Mappers.getMapper(ProductCategoryMapper.class);
        List<ProductCategory> list2 = list.stream().map(ProductCategoryTOProduct::getProductCategory).toList();
        List<ProductCategoryDTO> list3 = list2.stream().map(productCategoryMapper::toDto).toList();
        return list3;
    }
    @Mapping(target = "user",source = "ownerDTO")
    @Mapping(target = "productCategoryTOProducts",ignore = true)
    @Mapping(target = "orderElems", ignore = true)
    Product toEntity(ProductDTO productDTO);
}
