package com.example.demo.ServiceImpl;

import com.example.demo.Dto.CreateNewProductDto;
import com.example.demo.Dto.ProductDto;
import com.example.demo.Entity.Product;
import com.example.demo.Exceptions.ProductNotFoundException;
import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.Mapper.ProductMapper;
import com.example.demo.Service.IProductService;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ProductDto addProduct(CreateNewProductDto dto) {
        Product product=productMapper.convertCreateNewProductDtoToProduct(dto);
        Product savedProduct=productRepository.save(product);
        return productMapper.convertProductToProductDto(savedProduct);
    }

    @Override
    public Optional<ProductDto> getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                ()-> new ProductNotFoundException("Product not found with ID: " + id)
        );
        ProductDto dto=productMapper.convertProductToProductDto(product);
        return Optional.ofNullable(dto);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Long id, Product updatedProduct) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));

        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.setPrice(updatedProduct.getPrice());
        product.setImageUrl(updatedProduct.getImageUrl());

        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with ID: " + id);
        }
        productRepository.deleteById(id);
    }
}

