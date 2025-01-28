package com.example.portfolio.controller;

import com.example.portfolio.model.User;
import com.example.portfolio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5174") // Allows requests from the frontend
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Get all users (for listing all users if necessary)
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build(); // Respond with 204 if no users found
        }
        return ResponseEntity.ok(users); // Respond with 200 and the list of users
    }

    // Get user by ID (fetch single user with related entities like skills, projects, etc.)
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id); // Fetch user along with related data
        if (user == null) {
            return ResponseEntity.notFound().build(); // Respond with 404 if user not found
        }
        return ResponseEntity.ok(user); // Respond with 200 and the user
    }


}
