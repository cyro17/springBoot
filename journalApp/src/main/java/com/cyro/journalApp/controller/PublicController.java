package com.cyro.journalApp.controller;


import com.cyro.journalApp.entity.User;
import com.cyro.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @GetMapping("/heath-check")
    public String healthCheck(){
        return  "OK";
    }

    @PostMapping("signup")
    public void signup(@RequestBody User user){
        User newUser = new User();
        newUser.setUserName(user.getUserName());
        newUser.setPassword(user.getPassword());
        userService.saveNewUser(newUser);
    }

}
