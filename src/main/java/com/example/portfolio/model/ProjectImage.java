package com.example.portfolio.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ProjectImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many-to-one relationship with Project
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false) // project_id is the foreign key
    private Project project;

    private String imageUrl; // URL of the image
}
