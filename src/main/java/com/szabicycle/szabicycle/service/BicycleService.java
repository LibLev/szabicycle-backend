package com.szabicycle.szabicycle.service;

import com.szabicycle.szabicycle.bucket.BucketName;
import com.szabicycle.szabicycle.model.Bicycle;
import com.szabicycle.szabicycle.model.TypeOfBicycle;
import com.szabicycle.szabicycle.repository.BicycleRepository;
import lombok.AllArgsConstructor;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static org.apache.http.entity.ContentType.*;

@Service
@AllArgsConstructor
public class BicycleService {

    private BicycleRepository bicycleRepository;

    private final FileStore fileStore;

    public TypeOfBicycle setBicycleType(String data){
        TypeOfBicycle type = null;
        if (data.equals("road")){
            type = TypeOfBicycle.ROAD;
        }
        if (data.equals("gravel")){
            type = TypeOfBicycle.GRAVEL;
        }
        if (data.equals("cyclecross")){
            type = TypeOfBicycle.CYCLECROSS;
        }
        if (data.equals("track")){
            type = TypeOfBicycle.TRACK;
        }
        if (data.equals("city")){
            type = TypeOfBicycle.CITY;
        }
        if (data.equals("trekking")){
            type = TypeOfBicycle.TREKKING;
        }
        if (data.equals("mountain")){
            type = TypeOfBicycle.MOUNTAIN;
        }
        return type;
    }

    public Bicycle saveBicycle(Map<String, String> data){
        TypeOfBicycle type = setBicycleType(data.get("typeOfBicycle"));
        Bicycle newBike = Bicycle.builder()
                .typeOfBicycle(type)
                .name(data.get("name"))
                .brand(data.get("brand"))
                .frame(data.get("frame"))
                .fork(data.get("fork"))
                .groupSet(data.get("groupSet"))
                .shifters(data.get("shifters"))
                .callipers(data.get("callipers"))
                .breaks(data.get("breaks"))
                .seatPost(data.get("seatPost"))
                .saddle(data.get("saddle"))
                .stem(data.get("stem"))
                .handlebar(data.get("handlebar"))
                .barTape(data.get("barTape"))
                .pedal(data.get("pedal"))
                .wheels(data.get("wheels"))
                .details(data.get("details"))
                .price(Integer.parseInt(data.get("price")))
                .build();
        return bicycleRepository.save(newBike);
    }

    public Bicycle updateBicycle(Map<String, String> data){
        String name, brand, details, frame, fork, groupSet, shifters, breaks, callipers,
                wheels, saddle, seatPost, stem, handlebar, barTape, pedal;
        int price;
        Optional<Bicycle> optBike = bicycleRepository.findById(Long.parseLong(data.get("id")));
        Bicycle actBike = optBike.get();
        List<String> items = Arrays.asList(data.get("imgUris").split(","));
        TypeOfBicycle type = setBicycleType(data.get("typeOfBicycle"));
        if (data.get("name").equals("")){
            name = actBike.getName();
        }else {
            name =data.get("name");
        }
        if (data.get("brand").equals("")){
            brand = actBike.getBrand();
        }else {
            brand =data.get("brand");
        }
        if (data.get("details").equals("")){
            details = actBike.getDetails();
        }else {
            details =data.get("details");
        }
        if (data.get("frame").equals("")){
            frame = actBike.getFrame();
        }else {
            frame =data.get("frame");
        }
        if (data.get("fork").equals("")){
            fork = actBike.getFork();
        }else {
            fork =data.get("fork");
        }
        if (data.get("groupSet").equals("")){
            groupSet = actBike.getGroupSet();
        }else {
            groupSet =data.get("groupSet");
        }
        if (data.get("shifters").equals("")){
            shifters = actBike.getShifters();
        }else {
            shifters =data.get("shifters");
        }
        if (data.get("breaks").equals("")){
            breaks = actBike.getBreaks();
        }else {
            breaks =data.get("breaks");
        }
        if (data.get("callipers").equals("")){
            callipers = actBike.getCallipers();
        }else {
            callipers =data.get("callipers");
        }
        if (data.get("wheels").equals("")){
            wheels = actBike.getShifters();
        }else {
            wheels =data.get("wheels");
        }
        if (data.get("saddle").equals("")){
            saddle = actBike.getSaddle();
        }else {
            saddle =data.get("saddle");
        }
        if (data.get("seatPost").equals("")){
            seatPost = actBike.getSeatPost();
        }else {
            seatPost =data.get("seatPost");
        }
        if (data.get("stem").equals("")){
            stem = actBike.getStem();
        }else {
            stem =data.get("stem");
        }
        if (data.get("handlebar").equals("")){
            handlebar = actBike.getHandlebar();
        }else {
            handlebar =data.get("handlebar");
        }
        if (data.get("barTape").equals("")){
            barTape = actBike.getBarTape();
        }else {
            barTape =data.get("barTape");
        }
        if (data.get("pedal").equals("")){
            pedal = actBike.getPedal();
        }else {
            pedal =data.get("pedal");
        }
        if (data.get("price").equals("")){
            price = actBike.getPrice();
        }else {
            price =Integer.parseInt(data.get("price"));
        }
        actBike = Bicycle.builder()
                .typeOfBicycle(type)
                .name(name)
                .brand(brand)
                .frame(frame)
                .fork(fork)
                .groupSet(groupSet)
                .shifters(shifters)
                .callipers(callipers)
                .breaks(breaks)
                .seatPost(seatPost)
                .saddle(saddle)
                .stem(stem)
                .handlebar(handlebar)
                .barTape(barTape)
                .pedal(pedal)
                .wheels(wheels)
                .details(details)
                .imgUris(items)
                .price(price)
                .build();
        bicycleRepository.save(actBike);
        return actBike;
    }

    private Map<String, String> extractMetadata(MultipartFile file) {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        return metadata;
    }

    private Bicycle getBicycleOrThrow(Long id) {
        return bicycleRepository.findAll()
                .stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("Bicycle %s not found", id)));
    }

    private void isImage(MultipartFile file) {
        if (!Arrays.asList(IMAGE_JPEG.getMimeType(), IMAGE_PNG.getMimeType(), IMAGE_GIF.getMimeType()).contains(file.getContentType())){
            throw new IllegalStateException("File must be an image [" + file.getContentType() + "]");
        }
    }

    private void isFileEmpty(MultipartFile file) {
        if (file.isEmpty()){
            throw new IllegalStateException("Cannot upload an empty file [" + file.getSize() + "]");
        }
    }

    public void uploadBicycleImage(Long id, MultipartFile file) {
        isFileEmpty(file);
        isImage(file);
        Bicycle bicycle = getBicycleOrThrow(id);

        Map<String, String> metadata = extractMetadata(file);

        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), "bicycle-" + bicycle.getId());
        String fileName = String.format("%s-%s", file.getOriginalFilename(),UUID.randomUUID());
        try {
            List<String> images = bicycle.getImgUris();
            images.add(fileName);
            bicycle.setImgUris(images);
            bicycleRepository.save(bicycle);
            fileStore.save(path,fileName, Optional.of(metadata),file.getInputStream());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public byte[] downloadBicycleImage(Long id, int index) {
        Bicycle bicycle = getBicycleOrThrow(id);
        String path = String.format("%s/%s",
                BucketName.PROFILE_IMAGE.getBucketName(),
                "bicycle-"+bicycle.getId());
        String key = bicycle.getImgUris().get(index);
        return fileStore.download(path, key);
    }

    public void deleteImage(Long id, int index){
        Bicycle bicycle = getBicycleOrThrow(id);
        String path = String.format("%s/%s",
                BucketName.PROFILE_IMAGE.getBucketName(),
                "bicycle-"+bicycle.getId());
        String key = bicycle.getImgUris().get(index);
        fileStore.delete(path,key);
    }

    public void deleteBicycle(Long id) {
        Bicycle bicycle = getBicycleOrThrow(id);
        List<String> deletePictures = bicycle.getImgUris();
        for (int i = 0; i < deletePictures.size(); i++) {
            deleteImage(id,i);
        }
        bicycleRepository.delete(bicycle);
    }
}
