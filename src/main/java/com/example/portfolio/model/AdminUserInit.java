package com.example.portfolio.model;



import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AdminUserInit {
    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "adminpassword";  // Admin password
        String encodedPassword = passwordEncoder.encode(rawPassword);  // Encode password
        System.out.println("Encoded password: " + encodedPassword);  // Output the encoded password
    }
}
