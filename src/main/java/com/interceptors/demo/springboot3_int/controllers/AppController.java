package com.interceptors.demo.springboot3_int.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;
import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api")
public class AppController {

    @GetMapping("/version")
    public Map<String,String> getVersion() {
        
        return Collections.singletonMap("version", "1.0.1");
    }

    @GetMapping("/alfa")
    public Map<String,String> getAlfa() {
        
        return Collections.singletonMap("message", "i am alfa...");
    }

    @GetMapping("/beta")
    public Map<String,String> getBeta() {
        
        return Collections.singletonMap("message", "i am beta...");
    }

}
