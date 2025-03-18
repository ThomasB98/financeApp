package com.example.demo.Mapper;

import com.example.demo.Dto.CreateNewProductDto;
import com.example.demo.Dto.ProductDto;
import com.example.demo.Entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product convertProductDtoToProduct(ProductDto dto){
        Product product=new Product();
        product.setId(dto.getId());
        product.setImageUrl(dto.getImageUrl());
        product.setPrice(dto.getPrice());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());

        return product;
    }

    public ProductDto convertProductToProductDto(Product product){
        ProductDto dto=new ProductDto();
        dto.setId(product.getId());
        dto.setDescription(product.getDescription());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setImageUrl(product.getImageUrl());

        return dto;
    }

    public Product convertCreateNewProductDtoToProduct(CreateNewProductDto dto){
        Product product=new Product();
        product.setDescription(dto.getDescription());
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setImageUrl(dto.getImageUrl());

        return product;
    }

}
