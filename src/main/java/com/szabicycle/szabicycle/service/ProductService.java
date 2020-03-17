package com.szabicycle.szabicycle.service;

import com.szabicycle.szabicycle.model.Product;
import com.szabicycle.szabicycle.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void saveProduct(Map<String, String> data){
        Product newProduct = Product.builder()
                .name(data.get("name"))
                .brand(data.get("brand"))
                .details(data.get("details"))
                .price(Integer.parseInt(data.get("price")))
                .imgUri(data.get("imgUri"))
                .build();
        productRepository.save(newProduct);
    }
}
