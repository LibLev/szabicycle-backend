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
@CrossOrigin
public class ComponentController {

    ComponentService componentService;
    ComponentRepository componentRepository;

    @GetMapping("/get-all-component")
    public List<Component> getAllComponent(){
        return componentRepository.findAll();
    }

    @GetMapping("/get-all-bartape-component")
    public List<Component> getAllBartapeComponent(){
        return componentRepository.findAllBartape();
    }

    @GetMapping("/get-all-break-component")
    public List<Component> getAllBreakComponent(){
        return componentRepository.findAllBreak();
    }

    @GetMapping("/get-all-calliper-component")
    public List<Component> getAllCalliperComponent(){
        return componentRepository.findAllCalliper();
    }

    @GetMapping("/get-all-crankset-component")
    public List<Component> getAllCranksetComponent(){
        return componentRepository.findAllCrankset();
    }

    @GetMapping("/get-all-fork-component")
    public List<Component> getAllForkComponent(){
        return componentRepository.findAllFork();
    }

    @GetMapping("/get-all-frame-component")
    public List<Component> getAllFrameComponent(){
        return componentRepository.findAllFrame();
    }

    @GetMapping("/get-all-groupset-component")
    public List<Component> getAllGroupsetComponent(){
        return componentRepository.findAllGroupset();
    }

    @GetMapping("/get-all-handlebar-component")
    public List<Component> getAllHandlebarComponent(){
        return componentRepository.findAllHandlebar();
    }

    @GetMapping("/get-all-pedal-component")
    public List<Component> getAllPedalComponent(){
        return componentRepository.findAllPedal();
    }

    @GetMapping("/get-all-saddle-component")
    public List<Component> getAllSaddleComponent(){
        return componentRepository.findAllSaddle();
    }

    @GetMapping("/get-all-seatpost-component")
    public List<Component> getAllSeatpostComponent(){
        return componentRepository.findAllSeatpost();
    }

    @GetMapping("/get-all-shifter-component")
    public List<Component> getAllShifterComponent(){
        return componentRepository.findAllShifter();
    }

    @GetMapping("/get-all-stem-component")
    public List<Component> getAllStemComponent(){
        return componentRepository.findAllStem();
    }

    @GetMapping("/get-all-wheel-component")
    public List<Component> getAllWheelComponent(){
        return componentRepository.findAllWheel();
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
