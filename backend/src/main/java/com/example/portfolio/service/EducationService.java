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
    public List<Education> getAllEducation() {
        return educationRepository.findAll();
    }

}
