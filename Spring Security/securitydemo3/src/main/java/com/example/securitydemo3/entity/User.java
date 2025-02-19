package com.example.securitydemo3.entity;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import org.springframework.data.mongodb.core.mapping.Document;


import java.util.List;

@Document(collection = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private ObjectId id;
    @Indexed(unique = true)
    @NonNull
    private String userName;

    @NonNull
    private String password;

    private List<String> roles;
}