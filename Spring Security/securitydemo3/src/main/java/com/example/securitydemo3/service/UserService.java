package com.example.securitydemo3.service;


import com.example.securitydemo3.entity.User;
import com.example.securitydemo3.repo.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();

    public boolean saveNewUser(User user){
        try{
            user.setPassword(encoder.encode(user.getPassword()));
            user.setRoles(List.of("USER"));
            userRepo.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean saveAdmin(User user){
        try{
            user.setPassword(encoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER", "ADMIN"));
            userRepo.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void saveUser(User user){
        userRepo.save(user);
    }

    public List<User> getAll(){
        return  userRepo.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return  userRepo.findById(id);
    }

    public User findByUserName(String userName){
        return userRepo.findByUserName(userName);
    }

    public String verify(User user){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword())
        );
        if(authentication.isAuthenticated()) return jwtService.generateToken(user.getUserName());
        return "failure";
    }
}

