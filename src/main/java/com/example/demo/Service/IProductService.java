package com.example.demo.Service;

import com.example.demo.Dto.CreateNewProductDto;
import com.example.demo.Dto.ProductDto;
import com.example.demo.Entity.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    ProductDto addProduct(CreateNewProductDto product);
    Optional<ProductDto> getProductById(Long id);
    List<Product> getAllProducts();
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
}
