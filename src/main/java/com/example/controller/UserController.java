package com.example.controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.example.dto.*;
import com.example.dto.AuthResponse;

import com.example.model.User;
import com.example.service.UserService;
import com.example.util.*;

import java.util.*;
@RestController
@RequestMapping("/api/users")

public class UserController {
    
    @Autowired
    private UserService userService;
    //System.out.println("entered user controller apa");
    @Autowired
    private JwtUtil jwtUtil;
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
    System.out.println("entered the function in usercontroller to get all users");
    List<User> users = userService.getAllUsers(); // Ensure this method exists in UserService
    return ResponseEntity.ok(users);
}

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto) {
        System.out.println("Register endpoint hit!");
        User user = convertDtoToEntity(userDto);
        userService.register(user);
        return ResponseEntity.ok("User registered successfully");
    }

     // Helper method to convert UserDto to User
    private User convertDtoToEntity(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return user;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserDto userDto){
        System.out.println("Login endpoint hit !!");
        User user = userService.findByUsername(userDto.getUsername());
        if (user ==null || !(user.getPassword().equals(userDto.getPassword())))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        
        String token = jwtUtil.generateToken(user.getUsername());
        System.out.println("Generated token for the user is "+token);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
