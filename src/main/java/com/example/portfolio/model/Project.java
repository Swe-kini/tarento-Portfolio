package com.example.portfolio.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key

    private String title; // Project title
    private String description; // Short description
    private String details; // Detailed information about the project
    private String techStack; // Technologies used
    private String link; // GitHub or live demo link

    @Lob
    @Column(name = "image", columnDefinition = "bytea") // Store image as binary data
    private byte[] image; // Image stored as byte array

    @Column(columnDefinition = "TEXT") // Allows storing large HTML content
    private String explanation; // Detailed explanation in HTML format
}
