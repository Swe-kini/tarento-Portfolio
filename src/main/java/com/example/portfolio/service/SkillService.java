package com.example.portfolio.service;

import com.example.portfolio.model.Skill;
import com.example.portfolio.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    // Get all skills
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    // Get a specific skill by ID
    public Optional<Skill> getSkillById(Long id) {
        return skillRepository.findById(id);
    }

    // Create a new skill
    public Skill createSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    // Update an existing skill
    public Optional<Skill> updateSkill(Long id, Skill updatedSkill) {
        return skillRepository.findById(id).map(existingSkill -> {
            existingSkill.setName(updatedSkill.getName());
            existingSkill.setLevel(updatedSkill.getLevel());
            return skillRepository.save(existingSkill);
        });
    }

    // Delete a skill
    public boolean deleteSkill(Long id) {
        if (skillRepository.existsById(id)) {
            skillRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
