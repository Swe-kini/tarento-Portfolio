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

   
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

   
    public Optional<Skill> getSkillById(Long id) {
        return skillRepository.findById(id);
    }

    public Skill createSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    
    public Optional<Skill> updateSkill(Long id, Skill updatedSkill) {
        return skillRepository.findById(id).map(existingSkill -> {
            existingSkill.setName(updatedSkill.getName());
            existingSkill.setLevel(updatedSkill.getLevel());
            return skillRepository.save(existingSkill);
        });
    }

   
    public boolean deleteSkill(Long id) {
        if (skillRepository.existsById(id)) {
            skillRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
