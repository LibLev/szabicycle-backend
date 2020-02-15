package com.szabicycle.szabicycle.controller;

import com.szabicycle.szabicycle.entity.Bicycle;
import com.szabicycle.szabicycle.repository.BicycleRepository;
import com.szabicycle.szabicycle.service.BicycleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@RequestMapping("/bicycles")
@AllArgsConstructor
@Controller
public class BicycleController {

    private BicycleService bicycleService;
    private BicycleRepository bicycleRepository;

    @PostMapping("/add-new-bike")
    public Bicycle addNewBike(Map<String, String> data){
        Bicycle newBicycle = bicycleService.addNewBicycle(data);
        return newBicycle;
    }

    @GetMapping("/get-all-bikes")
    public List<Bicycle> getAllBikes(){
        return bicycleRepository.findAll();
    }
}
