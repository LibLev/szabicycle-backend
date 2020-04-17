package com.szabicycle.szabicycle.service;

import com.szabicycle.szabicycle.model.Bicycle;
import com.szabicycle.szabicycle.model.TypeOfBicycle;
import com.szabicycle.szabicycle.repository.BicycleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class BicycleService {

    @Autowired
    private BicycleRepository bicycleRepository;

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

    public void saveBicycle(Map<String, String> data){
        List<String> items = Arrays.asList(data.get("imgUris").split(","));
        TypeOfBicycle type = setBicycleType(data.get("typeOfBicycle"));
        Bicycle newBike = Bicycle.builder()
                .typeOfBicycle(type)
                .name(data.get("name"))
                .brand(data.get("brand"))
                .frame(data.get("frame"))
                .fork(data.get("fork"))
                .groupSet(data.get("groupset"))
                .shifters(data.get("shifters"))
                .callipers(data.get("callipers"))
                .breaks(data.get("breaks"))
                .seatPost(data.get("seatpost"))
                .saddle(data.get("saddle"))
                .stem(data.get("stem"))
                .handlebar(data.get("handlebar"))
                .barTape(data.get("barTape"))
                .pedal(data.get("pedal"))
                .wheels(data.get("wheels"))
                .details(data.get("details"))
                .imgUris(items)
                .price(Integer.parseInt(data.get("price")))
                .build();
        bicycleRepository.save(newBike);
    }
}
