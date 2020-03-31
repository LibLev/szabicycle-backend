package com.szabicycle.szabicycle.repository;

import com.szabicycle.szabicycle.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p where p.productType = com.szabicycle.szabicycle.model.ProductType.BICYCLE")
    List<Product> findAllBicycle();

    @Query("select p from Product p where p.productType = com.szabicycle.szabicycle.model.ProductType.COMPONENT")
    List<Product> findAllComponent();
}
