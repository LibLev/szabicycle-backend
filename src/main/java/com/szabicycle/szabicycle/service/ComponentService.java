package com.szabicycle.szabicycle.service;

import com.szabicycle.szabicycle.bucket.BucketName;
import com.szabicycle.szabicycle.model.*;
import com.szabicycle.szabicycle.repository.ComponentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static org.apache.http.entity.ContentType.*;

@Service
@AllArgsConstructor
public class ComponentService {

    private ComponentRepository componentRepository;
    private final FileStore fileStore;

    public TypeOfComponent setTypeOfComponent(String data){
        TypeOfComponent type = null;
        if (data.equals("break")){
            type = TypeOfComponent.BREAK;
        }
        if (data.equals("handlebar")){
            type = TypeOfComponent.HANDLEBAR;
        }
        if (data.equals("stem")){
            type = TypeOfComponent.STEM;
        }
        if (data.equals("seatPost")){
            type = TypeOfComponent.SEATPOST;
        }
        if (data.equals("saddle")){
            type = TypeOfComponent.SADDLE;
        }
        if (data.equals("barTape")){
            type = TypeOfComponent.BARTAPE;
        }
        if (data.equals("crankSet")){
            type = TypeOfComponent.CRANKSET;
        }
        if (data.equals("shifter")){
            type = TypeOfComponent.SHIFTER;
        }
        if (data.equals("groupSet")){
            type = TypeOfComponent.GROUPSET;
        }
        if (data.equals("wheel")){
            type = TypeOfComponent.WHEEL;
        }if (data.equals("pedal")){
            type = TypeOfComponent.PEDAL;
        }if (data.equals("frame")){
            type = TypeOfComponent.FRAME;
        }
        if (data.equals("fork")){
            type = TypeOfComponent.FORK;
        }
        if (data.equals("calliper")){
            type = TypeOfComponent.CALLIPER;
        }
        return type;
    }

    public Component saveComponent(Map<String, String> data){
        Component newComp = Component.builder()
                .name(data.get("name"))
                .brand(data.get("brand"))
                .details(data.get("details"))
                .price(Integer.parseInt(data.get("price")))
                .typeOfComponent(setTypeOfComponent(data.get("typeOfComponent")))
                .build();
        return componentRepository.save(newComp);
    }

    public Component updateProduct(Map<String, String> data) {
        String name;
        String brand;
        String details;
        int price;
        Optional<Component> optionalComp = componentRepository.findById(Long.parseLong(data.get("id")));
        Component actComp = optionalComp.get();
        List<String> items = Arrays.asList(data.get("imgUris").split(","));
        if (data.get("name").equals("")){
            name = actComp.getName();
        }else {
            name = data.get("name");
        }
        if (data.get("brand").equals("")){
            brand = actComp.getBrand();
        }else {
            brand = data.get("brand");
        }
        if (data.get("details").equals("")){
            details = actComp.getDetails();
        }else {
            details = data.get("details");
        }
        if (data.get("price").equals("")){
            price = actComp.getPrice();
        }else {
            price = Integer.parseInt(data.get("price"));
        }
        actComp = Component.builder()
                .id(actComp.getId())
                .name(name)
                .brand(brand)
                .details(details)
                .price(price)
                .imgUris(items)
                .typeOfComponent(setTypeOfComponent(data.get("typeOfComponent")))
                .build();
        componentRepository.save(actComp);
        return actComp;
    }

    private Map<String, String> extractMetadata(MultipartFile file) {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        return metadata;
    }

    private Component getComponentOrThrow(Long id) {
        return componentRepository.findAll()
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

    public void uploadComponentImage(Long id, MultipartFile file) {
        isFileEmpty(file);
        isImage(file);
        Component component = getComponentOrThrow(id);

        Map<String, String> metadata = extractMetadata(file);

        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), "component-" + component.getId());
        String fileName = String.format("%s", file.getOriginalFilename());
        try {
            List<String> images = component.getImgUris();
            images.add(fileName);
            component.setImgUris(images);
            componentRepository.save(component);
            fileStore.save(path,fileName, Optional.of(metadata),file.getInputStream());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public byte[] downloadBicycleImage(Long id, int index) {
        Component component = getComponentOrThrow(id);
        String path = String.format("%s/%s",
                BucketName.PROFILE_IMAGE.getBucketName(),
                "component-" + component.getId());
        String key = component.getImgUris().get(index);
        return fileStore.download(path, key);
    }

    public void deleteImage(Long id, int index){
        Component component = getComponentOrThrow(id);
        String path = String.format("%s/%s",
                BucketName.PROFILE_IMAGE.getBucketName(),
                "component-"+component.getId());
        String key = component.getImgUris().get(index);
        fileStore.delete(path,key);
    }

    public void deleteComponent(Long id) {
        Component component = getComponentOrThrow(id);
        List<String> deletePictures = component.getImgUris();
        for (int i = 0; i < deletePictures.size(); i++) {
            deleteImage(id,i);
        }
        componentRepository.delete(component);
    }

    public Component setMainPic(Long id, String mainImg) {
        Component component = getComponentOrThrow(id);
        component.setImgUri(mainImg);
        return componentRepository.save(component);
    }
}
