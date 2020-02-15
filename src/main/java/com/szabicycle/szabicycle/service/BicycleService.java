package com.szabicycle.szabicycle.service;

import com.szabicycle.szabicycle.entity.Bicycle;
import com.szabicycle.szabicycle.repository.BicycleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BicycleService {

    @Autowired
    private BicycleRepository bicycleRepository;

    public Bicycle addNewBicycle(Map<String, String> data){
        Bicycle newBike = Bicycle.builder()
                .brand(data.get("brand"))
                .series(data.get("series"))
                .about(data.get("about"))
                .price(Double.parseDouble(data.get("price")))
                .build();
        bicycleRepository.save(newBike);
        return newBike;
    }
}
