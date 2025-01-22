package com.example.portfolio.repository;

import com.example.portfolio.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    // Find skills by the associated user's ID
    List<Skill> findByUser_Id(Long userId);  // Corrected: Use `User_Id` for the relationship
}
