package com.szabicycle.szabicycle.service;

import com.szabicycle.szabicycle.model.Component;
import com.szabicycle.szabicycle.model.Product;
import com.szabicycle.szabicycle.model.ProductType;
import com.szabicycle.szabicycle.model.TypeOfComponent;
import com.szabicycle.szabicycle.repository.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ComponentService {

    @Autowired
    private ComponentRepository componentRepository;

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

    public void saveComponent(Map<String, String> data){
        List<String> items = Arrays.asList(data.get("imgUris").split(","));
        Component newComp = Component.builder()
                .name(data.get("name"))
                .brand(data.get("brand"))
                .details(data.get("details"))
                .price(Integer.parseInt(data.get("price")))
                .imgUris(items)
                .typeOfComponent(setTypeOfComponent(data.get("typeOfComponent")))
                .build();
        componentRepository.save(newComp);
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
}
