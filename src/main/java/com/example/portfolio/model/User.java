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

    @Lob
    @Column(name = "profile_pic", columnDefinition = "bytea")
    private byte[] profilePic; // Store image as binary data

    @Version
    private Integer version;  // Hibernate will handle optimistic locking
}
