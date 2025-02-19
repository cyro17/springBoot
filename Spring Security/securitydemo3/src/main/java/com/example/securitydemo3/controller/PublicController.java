package com.example.securitydemo3.controller;


import com.example.securitydemo3.entity.User;
import com.example.securitydemo3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping("check")
    public ResponseEntity<String> healthCheck(){
        return new ResponseEntity<>("OK ! ", HttpStatus.OK);
    }


    @PostMapping("signup")
    public ResponseEntity<?> signup(@RequestBody User user){
        try{
            User newUser = new User();
            newUser.setUserName(user.getUserName());
            newUser.setPassword(user.getPassword());
            userService.saveNewUser(newUser);
            return new ResponseEntity<>("success! ", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody User user){
        return new ResponseEntity<>(userService.verify(user), HttpStatus.ACCEPTED);
    }
}
