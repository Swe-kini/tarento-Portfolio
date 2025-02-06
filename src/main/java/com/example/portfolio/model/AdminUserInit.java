package com.example.portfolio.model;



import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AdminUserInit {
    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "adminpassword"; 
        String encodedPassword = passwordEncoder.encode(rawPassword); 
        System.out.println("Encoded password: " + encodedPassword);  
    }
}
