package com.szabicycle.szabicycle.service;

import com.szabicycle.szabicycle.model.Product;
import com.szabicycle.szabicycle.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void saveProduct(Map<String,String> data){
        Product newProduct = Product.builder()
                .name(data.get("name"))
                .brand(data.get("brand"))
                .details(data.get("details"))
                .price(Integer.parseInt(data.get("price")))
                .imgUris(Arrays.asList(data.get("imgUri1"),data.get("imgUri2"),data.get("imgUri3"),data.get("imgUri4"),data.get("imgUri5")))
                .build();
        productRepository.save(newProduct);
    }
}
