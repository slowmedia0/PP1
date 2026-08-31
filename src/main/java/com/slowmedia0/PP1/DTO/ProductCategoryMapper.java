package com.slowmedia0.PP1.DTO;


import com.slowmedia0.PP1.entity.ProductCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigInteger;

@Mapper(componentModel = "spring")
public interface ProductCategoryMapper {

    @Mapping(target = "parentId", source = "parentProductCategory.id")
    @Mapping(target = "childrenProductCategoryDTO",source = "childrenProductCategories")
    ProductCategoryDTO toDto (ProductCategory productCategory);

    @Mapping(target = "parentProductCategory", expression = "java(idToProductCategory(productCategoryDTO.parentId()))")
    @Mapping(target = "childrenProductCategories", ignore = true)
    @Mapping(target = "productCategoryTOProducts", ignore = true)
    ProductCategory toEntity (ProductCategoryDTO productCategoryDTO);
    default ProductCategory idToProductCategory (BigInteger parentId){
        if (parentId==null) return null;
        ProductCategory productCategory = new ProductCategory();
        productCategory.setId(parentId);
        return productCategory;
    }
}
