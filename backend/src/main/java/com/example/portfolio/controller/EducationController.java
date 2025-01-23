package com.example.portfolio.controller;

import com.example.portfolio.model.Education;
import com.example.portfolio.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/education")
public class EducationController {

    @Autowired
    private EducationService educationService;

    // Get all education records for a specific user
    @GetMapping
    public List<Education> getAllEducation() {
        return educationService.getAllEducation();
    }  
}
