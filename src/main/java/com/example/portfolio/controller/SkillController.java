package com.example.portfolio.controller;

import com.example.portfolio.model.Skill;
import com.example.portfolio.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")  // Allow requests from your frontend
@RestController
@RequestMapping("/api/skills")  // Base path for this controller
public class SkillController {

    @Autowired
    private SkillService skillService;

    // Get all skills
    @GetMapping
    public List<Skill> getAllSkills() {
        return skillService.getAllSkills();
    }

    // Get a specific skill by ID
    @GetMapping("/{id}")
    public ResponseEntity<Skill> getSkillById(@PathVariable Long id) {
        return skillService.getSkillById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create a new skill
    @PostMapping
    public ResponseEntity<Skill> createSkill(@RequestBody Skill skill) {
        Skill createdSkill = skillService.createSkill(skill);
        return ResponseEntity.status(201).body(createdSkill); // HTTP 201 Created
    }

    // Update an existing skill
    @PutMapping("/{id}")
    public ResponseEntity<Skill> updateSkill(@PathVariable Long id, @RequestBody Skill skill) {
        return skillService.updateSkill(id, skill)
                .map(updatedSkill -> ResponseEntity.ok().body(updatedSkill))
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete a skill
    @DeleteMapping("/{id}")
public ResponseEntity<String> deleteSkill(@PathVariable Long id) {
    if (skillService.deleteSkill(id)) {
        return ResponseEntity.ok("Skill deleted successfully"); // HTTP 200 OK with success message
    }
    return ResponseEntity.notFound().build(); // HTTP 404 Not Found
}
}