package com.arun.Products.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "users")
public class Users {

    @Id
    private String id;

    @Indexed(unique = true)        // username must be unique
    private String username;

    private String password;       // hashed password (BCrypt)

    private String role;           // ADMIN / USER
}
