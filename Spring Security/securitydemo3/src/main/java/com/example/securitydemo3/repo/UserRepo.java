package com.example.securitydemo3.repo;

import com.example.securitydemo3.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, ObjectId> {
    User findByUserName(String username);
}
