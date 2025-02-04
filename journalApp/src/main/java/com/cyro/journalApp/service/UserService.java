package com.cyro.journalApp.service;

import com.cyro.journalApp.entity.User;
import com.cyro.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public boolean saveNewUser(User user) {
        try {
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public void saveUser(User user){
        userRepository.save(user);
    }

    public List<User> getAll(){
        return  userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return  userRepository.findById(id);
    }

    public boolean deleteById(ObjectId id){
        userRepository.deleteById(id);
        return true;
    }

    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }
}