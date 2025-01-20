package com.example.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.Optional;
import com.example.util.JwtUtil;
import com.example.model.User;
import  com.example.repository.UserRepository;
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String register(User user) {
        // Hash the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered successfully";
    }
    public List<User> getAllUsers() {
        // Fetch users from the database
        //System.out.println("Entered getall users method");
            System.out.println("Fetching all users...");
            List<User> users = userRepository.findAll();
            System.out.println("Users: " + users);
        return userRepository.findAll(); // Assuming UserRepository is correctly set up
    }


    public String login(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return jwtUtil.generateToken(username); // Generate JWT token
            } else {
                throw new RuntimeException("Invalid password");
            }
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public User findByUsername(String username){
        System.out.println("Find by username endpoint hit in user service");
        return userRepository.findByUsername(username).orElse(null);
    }
}
