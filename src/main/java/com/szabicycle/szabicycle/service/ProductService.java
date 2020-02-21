package com.szabicycle.szabicycle.service;

import com.szabicycle.szabicycle.entity.Product;
import com.szabicycle.szabicycle.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product createNewProduct(Map<String, String> data) throws IOException {
        Product newProduct = Product.builder()
                .brand(data.get("brand"))
                .name(data.get("name"))
                .about(data.get("about"))
                .price(Double.parseDouble(data.get("price")))
                .productPic(createPic(data.get("productPic")))
                .build();
        productRepository.save(newProduct);
        return newProduct;
    }

    public byte[] createPic(String f) throws IOException {
        File file = new File(f);
        byte[] picInBytes = new byte[(int) file.length()];
        FileInputStream fileInputStream = new FileInputStream(file);
        fileInputStream.read(picInBytes);
        fileInputStream.close();
        return picInBytes;
    }
}
