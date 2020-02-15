package com.szabicycle.szabicycle.repository;

import com.szabicycle.szabicycle.entity.Bicycle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BicycleRepository extends JpaRepository<Bicycle, Long> {
}
