package com.szabicycle.szabicycle.controller;

import com.szabicycle.szabicycle.model.Component;
import com.szabicycle.szabicycle.payload.UploadFileResponse;
import com.szabicycle.szabicycle.repository.ComponentRepository;
import com.szabicycle.szabicycle.service.ComponentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public Component saveProduct(@RequestBody Map<String, String> product){
        return componentService.saveComponent(product);
    }

    @PostMapping(
            path = "component/image/upload/{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public UploadFileResponse uploadComponentImage(@PathVariable("id")Long id, @RequestParam("file") MultipartFile file){
        componentService.uploadComponentImage(id,file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(file.getName())
                .toUriString();

        return new UploadFileResponse(file.getName(),fileDownloadUri,file.getContentType(), file.getSize());
    }

    @PostMapping("component/upload-multiple-picture/{id}")
    public List<UploadFileResponse> uploadMultiplePicture(@PathVariable("id") Long id, @RequestParam("files") MultipartFile[] files){
        return Arrays.stream(files)
                .map(file -> uploadComponentImage(id, file))
                .collect(Collectors.toList());
    }

    @GetMapping("component/image/download/{id}/{index}")
    public byte[] downloadComponentImage(@PathVariable("id")Long id,@PathVariable("index")int index){
        return componentService.downloadBicycleImage(id,index);
    }

    @PostMapping("/updateComponent")
    public Component updateProduct(@RequestBody Map<String, String> data){
        return componentService.updateProduct(data);
    }

    @DeleteMapping("/deleteComponent/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) throws IOException {
        componentService.deleteComponent(id);
        final String response = "Bicycle with" + id + "id has been deleted.";
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
