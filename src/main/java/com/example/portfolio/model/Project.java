package com.example.portfolio.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @Lob // Specifies that the column should handle large text or binary data
    @Column(columnDefinition = "TEXT") // Ensures the database column uses the TEXT type
    private String explanation; // Detailed explanation of the project

    // One-to-many relationship with ProjectImage
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProjectImage> images; // List of images associated with the project
}
