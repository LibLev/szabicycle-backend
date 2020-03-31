package com.szabicycle.szabicycle.service;

import com.szabicycle.szabicycle.model.Product;
import com.szabicycle.szabicycle.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void saveProduct(Map<String,String> data){
        List<String> items = Arrays.asList(data.get("imgUris").split(","));
        Product newProduct = Product.builder()
                .name(data.get("name"))
                .brand(data.get("brand"))
                .details(data.get("details"))
                .price(Integer.parseInt(data.get("price")))
                .imgUris(items)
                .build();
        productRepository.save(newProduct);
    }
}
