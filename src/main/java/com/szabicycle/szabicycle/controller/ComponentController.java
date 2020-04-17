package com.szabicycle.szabicycle.controller;

import com.szabicycle.szabicycle.model.Component;
import com.szabicycle.szabicycle.repository.ComponentRepository;
import com.szabicycle.szabicycle.service.ComponentService;
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
public class ComponentController {

    ComponentService componentService;
    ComponentRepository componentRepository;

    @GetMapping("/get-all-component")
    public List<Component> getAllComponent(){
        return componentRepository.findAll();
    }

    @GetMapping("component/{id}")
    public Optional<Component> getComponentById(@PathVariable("id") Long id){
        return componentRepository.findById(id);
    }

    @PostMapping("/saveComponent")
    public void saveProduct(@RequestBody Map<String, String> product){
        componentService.saveComponent(product);
    }

    @PostMapping("/updateComponent")
    public Component updateProduct(@RequestBody Map<String, String> data){
        return componentService.updateProduct(data);
    }

    @DeleteMapping("/deleteComponent/{id}")
    public void deleteProduct(@PathVariable Long id) throws IOException {
        Optional<Component> opProd = componentRepository.findById(id);
        Component component = opProd.get();
        List<String> deletePictures = component.getImgUris();
        componentRepository.deleteById(id);
        for (String s : deletePictures) {
            Path pictureToDelete = Paths.get("/home/levente/Desktop/pet/szabiCycle/szaby-cycle-backend/photos/" + s);
            Files.delete(pictureToDelete);
        }
    }

}
