package com.cyro.journalApp.controller;

import com.cyro.journalApp.entity.User;
import com.cyro.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> all = userService.getAll();
        if(all != null && !all.isEmpty())
            return  new ResponseEntity<>(all, HttpStatus.OK);
        return  new ResponseEntity<>(all, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        try{
            userService.saveNewUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }catch (Exception e) {
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{userName}")
    public  ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String userName){
        try {
            User userInDb = userService.findByUserName(userName);
            if(userInDb != null ){
                userInDb.setUserName(user.getUserName());
                userInDb.setPassword(user.getPassword());
                userService.saveUser(userInDb);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (Exception e) {
            throw new RuntimeException("some error occured while updating user!!!!");
        }
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
