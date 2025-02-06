package com.example.portfolio.controller;

import com.example.portfolio.model.User;
import com.example.portfolio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
// import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173") // Allows frontend requests
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return users.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(users);
    }

    // Get a user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    
    // Create a new user with profile picture upload
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<User> createUser(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("linkedin") String linkedin,
            @RequestParam("github") String github,
            @RequestParam("description") String description,
            @RequestParam(value = "profilePic", required = false) MultipartFile profilePic) {

        try {
            User user = userService.createUser(name, email, phone, linkedin, github, description, profilePic);
            return ResponseEntity.ok(user);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // Update user profile with an optional profile picture
    @PutMapping(value = "/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<User> updateUser(
            @PathVariable Long id,
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("linkedin") String linkedin,
            @RequestParam("github") String github,
            @RequestParam("description") String description,
            @RequestParam(value = "profilePic", required = false) MultipartFile profilePic) {

        try {
            User updatedUser = userService.updateUser(id, name, email, phone, linkedin, github, description, profilePic);
            return updatedUser != null ? ResponseEntity.ok(updatedUser) : ResponseEntity.notFound().build();
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // Delete a user
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id)
                ? ResponseEntity.ok("User deleted successfully")
                : ResponseEntity.notFound().build();
    }
    

}
