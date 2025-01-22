package com.example.portfolio.service;


import com.example.portfolio.model.Project;
import com.example.portfolio.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    // Get all projects
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    // Get project by ID
    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

    // Add a new project
    public Project addProject(Project project) {
        return projectRepository.save(project);
    }

    // Update project details
    public Project updateProject(Long id, Project updatedProject) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        project.setTitle(updatedProject.getTitle());
        project.setDescription(updatedProject.getDescription());
        project.setDetails(updatedProject.getDetails());
        project.setTechStack(updatedProject.getTechStack());
        project.setLink(updatedProject.getLink());
        return projectRepository.save(project);
    }

    // Delete a project by ID
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}
