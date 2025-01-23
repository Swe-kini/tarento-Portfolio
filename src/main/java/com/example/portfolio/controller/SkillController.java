package com.example.portfolio.controller;

import com.example.portfolio.model.Skill;
import com.example.portfolio.service.SkillService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    @Autowired
    private SkillService skillService;

    // Get all skills for a specific user
    @GetMapping
    public List<Skill> getAllSkills() {
        return skillService.getAllSkills();
    }
}
