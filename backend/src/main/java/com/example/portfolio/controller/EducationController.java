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
    @GetMapping("/user/{userId}")
    public List<Education> getEducationByUserId(@PathVariable Long userId) {
        return educationService.getEducationByUserId(userId);
    }

    // Add a new education record
    @PostMapping
    public Education addEducation(@RequestBody Education education) {
        return educationService.addEducation(education);
    }

    // Update an existing education record
    @PutMapping("/{id}")
    public Education updateEducation(@PathVariable Long id, @RequestBody Education updatedEducation) {
        return educationService.updateEducation(id, updatedEducation);
    }

    // Delete an education record by ID
    @DeleteMapping("/{id}")
    public void deleteEducation(@PathVariable Long id) {
        educationService.deleteEducation(id);
    }
}
