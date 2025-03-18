package com.example.demo.Controllers;

import com.example.demo.Dto.CreateNewProductDto;
import com.example.demo.Dto.ProductDto;
import com.example.demo.Exceptions.ProductNotFoundException;
import com.example.demo.Service.IProductService;
import com.example.demo.Utils.ResponseBody.ResponseBody;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private IProductService productService;

    @PostMapping("/add")
    public ResponseEntity<ResponseBody<ProductDto>> addProduct(@Valid @RequestBody CreateNewProductDto productDto)
    {
        return new ResponseEntity<>(new ResponseBody<>(
                "Product added successfully",
                true,
                HttpStatus.CREATED,
                productService.addProduct(productDto)), HttpStatus.CREATED
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<ResponseBody<ProductDto>> getProductById(@PathVariable Long id)
    {
        return new ResponseEntity<>(new ResponseBody<>(
                "Product found", true, HttpStatus.OK, productService.getProductById(id).orElseThrow(
                ()->new ProductNotFoundException("Product not found with id "+id)
        )), HttpStatus.OK
        );
    }
}
