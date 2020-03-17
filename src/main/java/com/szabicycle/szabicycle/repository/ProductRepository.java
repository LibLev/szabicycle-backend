package com.szabicycle.szabicycle.repository;

import com.szabicycle.szabicycle.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
