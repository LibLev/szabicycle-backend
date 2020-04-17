package com.szabicycle.szabicycle.service;

import com.szabicycle.szabicycle.model.Bicycle;
import com.szabicycle.szabicycle.model.TypeOfBicycle;
import com.szabicycle.szabicycle.repository.BicycleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
                .imgUris(items)
                .price(Integer.parseInt(data.get("price")))
                .build();
        bicycleRepository.save(newBike);
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
}
