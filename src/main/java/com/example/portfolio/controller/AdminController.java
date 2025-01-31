package com.example.portfolio.controller;

import com.example.portfolio.model.AppUser;
import com.example.portfolio.repository.AppUserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/app_users")
    public ResponseEntity<String> createUser(@RequestBody AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt password
        userRepository.save(user);
        return ResponseEntity.ok("User created successfully");
    }
}
