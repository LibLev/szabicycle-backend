package com.szabicycle.szabicycle.repository;

import com.szabicycle.szabicycle.model.Bicycle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BicycleRepository extends JpaRepository<Bicycle, Long> {
}
