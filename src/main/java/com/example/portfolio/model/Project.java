package com.example.portfolio.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description; // Short description
    private String details; // Detailed information about the project
    private String techStack; // Technologies used
    private String link; // GitHub or live demo link
}
