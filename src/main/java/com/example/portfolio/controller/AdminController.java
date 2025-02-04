package com.example.portfolio.controller;

import com.example.portfolio.model.AppUser;
import com.example.portfolio.repository.AppUserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@CrossOrigin(origins = "http://localhost:5173") 
@RestController
@RequestMapping("/api")
public class AdminController {

    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminController(AppUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/admin")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @PostMapping("/admin")
    public ResponseEntity<String> createUser(@RequestBody AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt password before saving
        userRepository.save(user);
        return ResponseEntity.ok("User created successfully");
    }

    @PostMapping("/login")
public ResponseEntity<String> login(@RequestBody AppUser user) {
    Optional<AppUser> existingUser = userRepository.findByUsername(user.getUsername());

    if (existingUser.isPresent() && passwordEncoder.matches(user.getPassword(), existingUser.get().getPassword())) {
        return ResponseEntity.ok("Authenticated"); // Return success message
    } else {
        return ResponseEntity.status(401).body("Invalid credentials");
    }
}
@PutMapping("/admin/{id}")
public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody AppUser updatedUser) {
    Optional<AppUser> existingUser = userRepository.findById(id);

    if (existingUser.isPresent()) {
        AppUser user = existingUser.get();
        user.setUsername(updatedUser.getUsername());
        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }
        userRepository.save(user);
        return ResponseEntity.ok("User updated successfully");
    } else {
        return ResponseEntity.status(404).body("User not found");
    }
}

@DeleteMapping("/admin/{id}")
public ResponseEntity<String> deleteUser(@PathVariable Long id) {
    if (userRepository.existsById(id)) {
        userRepository.deleteById(id);
        return ResponseEntity.ok("User deleted successfully");
    } else {
        return ResponseEntity.status(404).body("User not found");
    }
}


}
