package com.example.portfolio.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;
    private String linkedin;
    private String github;
    private String description;
    private String profilePic; // URL to the profile picture

    @Version
    private Integer version;  // Hibernate will handle this field for optimistic locking

    
}
