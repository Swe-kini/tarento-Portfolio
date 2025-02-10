package com.example.portfolio.controller;

import com.example.portfolio.model.Skill;
import com.example.portfolio.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")  
@RestController
@RequestMapping("/api/skills")  
public class SkillController {

    @Autowired
    private SkillService skillService;

    @GetMapping
    public List<Skill> getAllSkills() {
        return skillService.getAllSkills();
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Skill> getSkillById(@PathVariable Long id) {
        return skillService.getSkillById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Skill> createSkill(@RequestBody Skill skill) {
        Skill createdSkill = skillService.createSkill(skill);
        return ResponseEntity.status(201).body(createdSkill); 
    }

   
    @PutMapping("/{id}")
    public ResponseEntity<Skill> updateSkill(@PathVariable Long id, @RequestBody Skill skill) {
        return skillService.updateSkill(id, skill)
                .map(updatedSkill -> ResponseEntity.ok().body(updatedSkill))
                .orElse(ResponseEntity.notFound().build());
    }

 
    @DeleteMapping("/{id}")
public ResponseEntity<String> deleteSkill(@PathVariable Long id) {
    if (skillService.deleteSkill(id)) {
        return ResponseEntity.ok("Skill deleted successfully"); 
    }
    return ResponseEntity.notFound().build(); 
}
}