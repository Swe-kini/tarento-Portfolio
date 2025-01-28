package com.example.portfolio.service;

import com.example.portfolio.model.Education;
import com.example.portfolio.repository.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EducationService {

    @Autowired
    private EducationRepository educationRepository;

    // Get all education records
    public List<Education> getAllEducation() {
        return educationRepository.findAll();
    }

    // Get a specific education record by ID
    public Optional<Education> getEducationById(Long id) {
        return educationRepository.findById(id);
    }

    // Create a single education record
    public Education createEducation(Education education) {
        return educationRepository.save(education);
    }

    // Create multiple education records (bulk insert)
    public List<Education> createEducation(List<Education> educationList) {
        return educationRepository.saveAll(educationList);
    }

    // Update an existing education record
    public Optional<Education> updateEducation(Long id, Education updatedEducation) {
        return educationRepository.findById(id).map(existingEducation -> {
            existingEducation.setDegree(updatedEducation.getDegree());
            existingEducation.setInstitution(updatedEducation.getInstitution());
            existingEducation.setYear(updatedEducation.getYear());
            return educationRepository.save(existingEducation);
        });
    }

    // Delete an education record by ID
    public boolean deleteEducation(Long id) {
        if (educationRepository.existsById(id)) {
            educationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
