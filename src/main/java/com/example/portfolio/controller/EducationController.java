package com.example.portfolio.controller;

import com.example.portfolio.model.Education;
import com.example.portfolio.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin(origins = "http://localhost:5173") 
@RequestMapping("/api/education")  
public class EducationController {

    @Autowired
    private EducationService educationService;

   
    @GetMapping
    public List<Education> getAllEducation() {
        return educationService.getAllEducation();
    }

  
    @GetMapping("/{id}")
    public ResponseEntity<Education> getEducationById(@PathVariable Long id) {
        return educationService.getEducationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    
    @PostMapping
    public ResponseEntity<Education> createEducation(@RequestBody Education education) {
        Education createdEducation = educationService.createEducation(education);
        return ResponseEntity.status(201).body(createdEducation); 
    }

   
    @PostMapping("/bulk")
    public ResponseEntity<List<Education>> createMultipleEducation(@RequestBody List<Education> educationList) {
        List<Education> createdEducationList = educationService.createEducation(educationList);
        return ResponseEntity.status(201).body(createdEducationList); 
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Education> updateEducation(@PathVariable Long id, @RequestBody Education education) {
        return educationService.updateEducation(id, education)
                .map(updatedEducation -> ResponseEntity.ok().body(updatedEducation))
                .orElse(ResponseEntity.notFound().build());
    }

   
    
    @DeleteMapping("/{id}")
public ResponseEntity<String> deleteEducation(@PathVariable Long id) {
    if (educationService.deleteEducation(id)) {
        return ResponseEntity.ok("Deleted successfully");
    }
    return ResponseEntity.notFound().build(); 
}

}
