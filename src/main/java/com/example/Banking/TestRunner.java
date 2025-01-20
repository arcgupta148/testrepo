package com.example.Banking;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.repository.UserRepository;
import java.util.*;
import com.example.model.User;
@Component
public class TestRunner implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Testing MongoDB repository...");
        System.out.println("Count of users: " + userRepository.count());
        List<String> test = new ArrayList<>();
            // Fetch all users
        List<User> users = userRepository.findAll();
    
    // Print users
        for (User user : users) {
        System.out.println("User: " + user.getUsername());
     }
    }
}
