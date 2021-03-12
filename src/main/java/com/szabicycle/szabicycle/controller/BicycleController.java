package com.szabicycle.szabicycle.controller;

import com.szabicycle.szabicycle.model.Bicycle;
import com.szabicycle.szabicycle.payload.UploadFileResponse;
import com.szabicycle.szabicycle.repository.BicycleRepository;
import com.szabicycle.szabicycle.service.BicycleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@CrossOrigin
@Slf4j
public class BicycleController {

    private BicycleRepository bicycleRepository;
    private BicycleService bicycleService;

    @GetMapping("/get-all-bicycle")
    public List<Bicycle> getAllBicycle() {
        return bicycleRepository.findAll();
    }

    @GetMapping("/get-all-road-bicycle")
    public List<Bicycle> getAllRoadBike(){
        return bicycleRepository.findAllRoadBicycle();
    }

    @GetMapping("/get-all-gravel-bicycle")
    public List<Bicycle> getAllGravelBike(){
        return bicycleRepository.findAllGravelBicycle();
    }

    @GetMapping("/get-all-cyclecross-bicycle")
    public List<Bicycle> getAllCyclecrossBike(){
        return bicycleRepository.findAllCyclecrossBicycle();
    }

    @GetMapping("/get-all-mtb-bicycle")
    public List<Bicycle> getAllMTBBike(){
        return bicycleRepository.findAllMTBBicycle();
    }

    @GetMapping("/get-all-track-bicycle")
    public List<Bicycle> getAllTrackBike(){
        return bicycleRepository.findAllTrackBicycle();
    }

    @GetMapping("/get-all-city-bicycle")
    public List<Bicycle> getAllCityBike(){
        return bicycleRepository.findAllCityBicycle();
    }

    @GetMapping("/get-all-trekking-bicycle")
    public List<Bicycle> getAllTrekkingBike(){
        return bicycleRepository.findAllTrekkingBicycle();
    }

    @GetMapping("bicycle/{id}")
    public Optional<Bicycle> getBikeById(@PathVariable("id") Long id) {
        return bicycleRepository.findById(id);
    }

    @PostMapping("/saveBicycle")
    public Bicycle saveProduct(@RequestBody Map<String, String> product) {
        log.info(product.toString());
        return bicycleService.saveBicycle(product);
    }

    @PostMapping(
            path = "bicycle/image/upload/{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public UploadFileResponse uploadBicycleImage(@PathVariable("id")Long id, @RequestParam("file")MultipartFile file){
        bicycleService.uploadBicycleImage(id,file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(file.getName())
                .toUriString();

        return new UploadFileResponse(file.getName(),fileDownloadUri,file.getContentType(), file.getSize());
    }

    @PostMapping("bicycle/upload-multiple-picture/{id}")
    public List<UploadFileResponse> uploadMultiplePicture(@PathVariable("id") Long id, @RequestParam("files") MultipartFile[] files){
        return Arrays.stream(files)
                .map(file -> uploadBicycleImage(id, file))
                .collect(Collectors.toList());
    }

    @PostMapping("bicycle/set-main-pic")
    public Bicycle setMainPic(@RequestBody Map<String, String> data){
        log.info(data.toString());
        return bicycleService.setMainPic(Long.parseLong(data.get("id")), data.get("mainImage"));
    }

    @GetMapping("bicycle/image/download/{id}/{index}")
    public byte[] downBicycleImage(@PathVariable("id")Long id,@PathVariable("index")int index){
        return bicycleService.downloadBicycleImage(id,index);
    }

    @PostMapping("/updateBicycle")
    public Bicycle updateProduct(@RequestBody Map<String, String> data) {
        return bicycleService.updateBicycle(data);
    }

    @DeleteMapping("/deleteBicycle/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        bicycleService.deleteBicycle(id);
        final String response = "Bicycle with" + id + "id has been deleted.";
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
