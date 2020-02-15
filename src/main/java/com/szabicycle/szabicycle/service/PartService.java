package com.szabicycle.szabicycle.service;

import com.szabicycle.szabicycle.entity.Part;
import com.szabicycle.szabicycle.repository.PartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class PartService {

    private PartRepository partRepository;

    public Part addNewPart(Map<String, String> data){
        Part newPart = Part.builder()
                .brand(data.get("brand"))
                .series(data.get("series"))
                .about(data.get("about"))
                .price(Double.parseDouble(data.get("price")))
                .build();
        partRepository.save(newPart);
        return newPart;
    }
}
