package com.example.portfolio.controller;

import com.example.portfolio.model.AppUser;
import com.example.portfolio.repository.AppUserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@CrossOrigin(origins = "http://localhost:5173") 
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminController(AppUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/app_users")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @PostMapping("/app_users")
    public ResponseEntity<String> createUser(@RequestBody AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt password before saving
        userRepository.save(user);
        return ResponseEntity.ok("User created successfully");
    }

    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AppUser user) {
        Optional<AppUser> existingUser = userRepository.findByUsername(user.getUsername());

        if (existingUser.isPresent() && passwordEncoder.matches(user.getPassword(), existingUser.get().getPassword())) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}
