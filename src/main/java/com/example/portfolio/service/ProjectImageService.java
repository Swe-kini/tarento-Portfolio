package com.example.portfolio.service;

import com.example.portfolio.model.ProjectImage;
import com.example.portfolio.repository.ProjectImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectImageService {

    private final ProjectImageRepository projectImageRepository;

    @Autowired
    public ProjectImageService(ProjectImageRepository projectImageRepository) {
        this.projectImageRepository = projectImageRepository;
    }

    // Fetch all images for a specific project
    public List<ProjectImage> getImagesByProjectId(Long projectId) {
        return projectImageRepository.findByProjectId(projectId);
    }
}
