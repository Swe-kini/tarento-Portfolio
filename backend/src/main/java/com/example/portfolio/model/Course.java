package com.example.portfolio.model;
import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String provider;
    private String completionDate;


}
