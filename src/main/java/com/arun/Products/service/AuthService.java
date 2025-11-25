package com.arun.Products.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.arun.Products.dto.LoginRequest;
import com.arun.Products.dto.LoginResponse;
import com.arun.Products.dto.UserRequest;
import com.arun.Products.jwt.JwtService;
import com.arun.Products.model.Users;
import com.arun.Products.repo.UserRepo;

@Service
public class AuthService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;   // ✅ REQUIRED for generating JWT token

    // REGISTER USER
    public String register(UserRequest request) {

        if (userRepo.findByUsername(request.getUsername()).isPresent()) {
            return "Username already exists!";
        }

        Users users = Users.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        userRepo.save(users);

        return "User registered successfully!";
    }

    // LOGIN & RETURN JWT TOKEN
    public LoginResponse login(LoginRequest request) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getUsername(),
                                request.getPassword()
                        )
                );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String token = jwtService.generateToken(userDetails); // ✅ generate JWT

        return new LoginResponse("Login successful!", token);
    }
}
