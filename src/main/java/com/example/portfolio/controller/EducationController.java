package com.example.portfolio.controller;

import com.example.portfolio.model.Education;
import com.example.portfolio.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5174")  // Allow requests from your frontend
@RestController
@RequestMapping("/api/education")  // Base path for this controller
public class EducationController {

    @Autowired
    private EducationService educationService;

    // Get all education records
    @GetMapping
    public List<Education> getAllEducation() {
        return educationService.getAllEducation();
    }

    // Get a specific education record by ID
    @GetMapping("/{id}")
    public ResponseEntity<Education> getEducationById(@PathVariable Long id) {
        return educationService.getEducationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create a single education record
    @PostMapping
    public ResponseEntity<Education> createEducation(@RequestBody Education education) {
        Education createdEducation = educationService.createEducation(education);
        return ResponseEntity.status(201).body(createdEducation); // HTTP 201 Created
    }

    // Create multiple education records (bulk insert)
    @PostMapping("/bulk")
    public ResponseEntity<List<Education>> createMultipleEducation(@RequestBody List<Education> educationList) {
        List<Education> createdEducationList = educationService.createEducation(educationList);
        return ResponseEntity.status(201).body(createdEducationList); // HTTP 201 Created
    }

    // Update an existing education record
    @PutMapping("/{id}")
    public ResponseEntity<Education> updateEducation(@PathVariable Long id, @RequestBody Education education) {
        return educationService.updateEducation(id, education)
                .map(updatedEducation -> ResponseEntity.ok().body(updatedEducation))
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete an education record
    
    @DeleteMapping("/{id}")
public ResponseEntity<String> deleteEducation(@PathVariable Long id) {
    if (educationService.deleteEducation(id)) {
        return ResponseEntity.ok("Deleted successfully"); // HTTP 200 OK with success message
    }
    return ResponseEntity.notFound().build(); // HTTP 404 Not Found
}

}
