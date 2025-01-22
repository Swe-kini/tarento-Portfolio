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
    public List<Skill> getSkillsByUserId(Long userId) {
        return skillRepository.findByUser_Id(userId);
    }

    // Add a new skill
    public Skill addSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    // Update an existing skill
    public Skill updateSkill(Long id, Skill updatedSkill) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found"));
        skill.setName(updatedSkill.getName());
        skill.setLevel(updatedSkill.getLevel());
        return skillRepository.save(skill);
    }

    // Delete a skill by ID
    public void deleteSkill(Long id) {
        skillRepository.deleteById(id);
    }
}
