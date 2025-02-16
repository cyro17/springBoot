package com.cyro.journalApp.controller;

import com.cyro.journalApp.entity.User;
import com.cyro.journalApp.repository.UserRepository;
import com.cyro.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<?> greeting(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        return  new ResponseEntity<>("Hey ! " + userName, HttpStatus.OK);
    }

    @PutMapping
    public  ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        User userInDb = userService.findByUserName(userName);
        userInDb.setUserName(user.getUserName());
        userInDb.setPassword(user.getPassword());
        userService.saveNewUser(userInDb);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserById(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(auth.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
