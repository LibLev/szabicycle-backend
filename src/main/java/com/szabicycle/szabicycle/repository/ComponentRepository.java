package com.szabicycle.szabicycle.repository;

import com.szabicycle.szabicycle.model.Component;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComponentRepository extends JpaRepository<Component, Long> {
}
