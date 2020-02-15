package com.szabicycle.szabicycle.controller;

import com.szabicycle.szabicycle.entity.Part;
import com.szabicycle.szabicycle.repository.PartRepository;
import com.szabicycle.szabicycle.service.PartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/parts")
@AllArgsConstructor
public class PartController {

    private PartRepository partRepository;
    private PartService partService;

    @PostMapping("/add-new-parts")
    public Part addNewPart(Map<String, String> data){
        Part newPart = partService.addNewPart(data);
        return newPart;
    }

    @GetMapping("get-all-parts")
    public List<Part> getAllPart(){
        return partRepository.findAll();
    }
}
