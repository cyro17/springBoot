package com.cyro.journalApp.controller;

import com.cyro.journalApp.entity.User;
import com.cyro.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("all-users")
    public ResponseEntity<?> getAllUsers(){
        List<User> all = userService.getAll();
        if(all != null && !all.isEmpty()) return  new ResponseEntity<>(all, HttpStatus.OK);
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("create-new-user")
    public ResponseEntity<String> createUser(@RequestBody User user){
        boolean created =  userService.saveAdmin(user);
        if(created) return  new ResponseEntity<>("user created", HttpStatus.CREATED);
        return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
    }

//    @PostMapping("create-new-user")
//    public ResponseEntity<?> createUser(@RequestBody User user){
//
//        boolean created =  userService.createNewAdmin(user);
//        if(created) return  new ResponseEntity<>("user created", HttpStatus.CREATED);
//        return new ResponseEntity<>("user could not be created.",  HttpStatus.BAD_REQUEST);
//    }
}
