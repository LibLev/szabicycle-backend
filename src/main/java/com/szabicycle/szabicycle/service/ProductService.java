package com.szabicycle.szabicycle.service;

import com.szabicycle.szabicycle.model.Product;
import com.szabicycle.szabicycle.model.ProductType;
import com.szabicycle.szabicycle.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void saveProduct(Map<String,String> data){
        List<String> items = Arrays.asList(data.get("imgUris").split(","));
        ProductType type;
        if (data.get("productType").equals("bicycle")){
            type = ProductType.BICYCLE;
        }else {
            type = ProductType.COMPONENT;
        }
        Product newProduct = Product.builder()
                .name(data.get("name"))
                .brand(data.get("brand"))
                .details(data.get("details"))
                .price(Integer.parseInt(data.get("price")))
                .imgUris(items)
                .productType(type)
                .build();
        productRepository.save(newProduct);
    }

    public Product updateProduct(Map<String, String> data) {
        String name;
        String brand;
        String details;
        int price;
        Optional<Product> optionalProduct = productRepository.findById(Long.parseLong(data.get("id")));
        Product actProd = optionalProduct.get();
        List<String> items = Arrays.asList(data.get("imgUris").split(","));
        ProductType type;
        if (data.get("productType").equals("bicycle")){
            type = ProductType.BICYCLE;
        }else {
            type = ProductType.COMPONENT;
        }
        if (data.get("name").equals("")){
            name = actProd.getName();
        }else {
            name = data.get("name");
        }
        if (data.get("brand").equals("")){
            brand = actProd.getBrand();
        }else {
            brand = data.get("brand");
        }
        if (data.get("details").equals("")){
            details = actProd.getDetails();
        }else {
            details = data.get("details");
        }
        if (data.get("price").equals("")){
            price = actProd.getPrice();
        }else {
            price = Integer.parseInt(data.get("price"));
        }
        actProd = Product.builder()
                .id(actProd.getId())
                .name(name)
                .brand(brand)
                .details(details)
                .price(price)
                .imgUris(items)
                .productType(type)
                .build();
        productRepository.save(actProd);
        return actProd;
    }
}
