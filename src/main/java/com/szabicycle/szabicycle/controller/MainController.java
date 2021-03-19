package com.szabicycle.szabicycle.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public String hello(){
        return ("<HTML><body> <a href=\"https://szabicycle.netlify.app/home\">Link clik to go</a></body></HTML>");
    }
}
