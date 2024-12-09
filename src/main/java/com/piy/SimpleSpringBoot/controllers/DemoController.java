package com.piy.SimpleSpringBoot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/")
    public String welcomePage(){
        return "Welcome to spring Boot";
    }

    @GetMapping("/getHello")
    public String getHello(){
        return "Getting Hello from Spring Boot";
    }
}
