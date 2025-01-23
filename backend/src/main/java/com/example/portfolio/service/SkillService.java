package com.example.portfolio.service;

import com.example.portfolio.model.Skill;
import com.example.portfolio.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    // Get all skills for a specific user
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }
}
