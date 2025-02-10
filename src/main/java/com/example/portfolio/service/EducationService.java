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

   
    public List<Education> getAllEducation() {
        return educationRepository.findAll();
    }

    public Optional<Education> getEducationById(Long id) {
        return educationRepository.findById(id);
    }

    
    public Education createEducation(Education education) {
        return educationRepository.save(education);
    }

    
    public List<Education> createEducation(List<Education> educationList) {
        return educationRepository.saveAll(educationList);
    }

    public Optional<Education> updateEducation(Long id, Education updatedEducation) {
        return educationRepository.findById(id).map(existingEducation -> {
            existingEducation.setDegree(updatedEducation.getDegree());
            existingEducation.setInstitution(updatedEducation.getInstitution());
            existingEducation.setYear(updatedEducation.getYear());
            return educationRepository.save(existingEducation);
        });
    }

   
    public boolean deleteEducation(Long id) {
        if (educationRepository.existsById(id)) {
            educationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
