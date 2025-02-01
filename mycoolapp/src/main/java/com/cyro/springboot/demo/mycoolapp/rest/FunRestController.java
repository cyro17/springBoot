package com.cyro.springboot.demo.mycoolapp.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {

    @GetMapping("/")
    public String sayHello(){
        return "hey there , helloo !!!";
    }

    @GetMapping("/workout")
    public String getDailyWorkout(){
        return "Run a hard 5k ";
    }
}
