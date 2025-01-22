package com.example.portfolio.service;

import com.example.portfolio.model.Education;
import com.example.portfolio.repository.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationService {

    @Autowired
    private EducationRepository educationRepository;

    // Get all education records for a specific user
    public List<Education> getEducationByUserId(Long userId) {
        return educationRepository.findByUser_Id(userId);
    }

    // Add a new education record
    public Education addEducation(Education education) {
        return educationRepository.save(education);
    }

    // Update an existing education record
    public Education updateEducation(Long id, Education updatedEducation) {
        Education education = educationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Education record not found"));
        education.setDegree(updatedEducation.getDegree());
        education.setInstitution(updatedEducation.getInstitution());
        education.setYear(updatedEducation.getYear());
        return educationRepository.save(education);
    }

    // Delete an education record by ID
    public void deleteEducation(Long id) {
        educationRepository.deleteById(id);
    }
}
