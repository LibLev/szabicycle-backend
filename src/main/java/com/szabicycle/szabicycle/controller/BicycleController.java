package com.szabicycle.szabicycle.controller;

import com.szabicycle.szabicycle.model.Bicycle;
import com.szabicycle.szabicycle.model.Product;
import com.szabicycle.szabicycle.repository.BicycleRepository;
import com.szabicycle.szabicycle.service.BicycleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class BicycleController {

    private BicycleRepository bicycleRepository;
    private BicycleService bicycleService;

    @GetMapping("/get-all-bicycle")
    public List<Bicycle> getAllBicycle() {
        return bicycleRepository.findAll();
    }

    @GetMapping("bicycle/{id}")
    public Optional<Bicycle> getBikeById(@PathVariable("id") Long id) {
        return bicycleRepository.findById(id);
    }


    @PostMapping("/saveBicycle")
    public void saveProduct(@RequestBody Map<String, String> product) {
        bicycleService.saveBicycle(product);
    }

    @PostMapping("/updateBicycle")
    public Bicycle updateProduct(@RequestBody Map<String, String> data) {
        return bicycleService.updateBicycle(data);
    }

    @DeleteMapping("/deleteBicycle/{id}")
    public void deleteProduct(@PathVariable Long id) throws IOException {
        Optional<Bicycle> opProd = bicycleRepository.findById(id);
        Bicycle bicycle = opProd.get();
        List<String> deletePictures = bicycle.getImgUris();
        bicycleRepository.deleteById(id);
        for (String s : deletePictures) {
            Path pictureToDelete = Paths.get("/home/levente/Desktop/pet/szabiCycle/szaby-cycle-backend/photos/" + s);
            Files.delete(pictureToDelete);
        }
    }

}
