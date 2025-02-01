package com.cyro.journalApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Healthcheck {
    @GetMapping("/heath-check")
    public String healthCheck(){
        return  "OK";
    }
}
