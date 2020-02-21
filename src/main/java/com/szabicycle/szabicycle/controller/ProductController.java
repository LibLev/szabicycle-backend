package com.szabicycle.szabicycle.controller;

import com.szabicycle.szabicycle.entity.Product;
import com.szabicycle.szabicycle.repository.ProductRepository;
import com.szabicycle.szabicycle.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
@CrossOrigin
public class ProductController {

    ProductRepository productRepository;
    ProductService productService;

    @PostMapping("/add-new-product")
    public Product addNewProduct(@RequestBody Map<String, String> data) throws IOException {
        return productService.createNewProduct(data);
    }

    @GetMapping("/get-all-product")
    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

}
