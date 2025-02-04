package com.example.portfolio.service;

import com.example.portfolio.model.Project;
import com.example.portfolio.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    // 1. Create a new project
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    // 2. Get all projects
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    // 3. Get a project by ID
    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    // 4. Update an existing project
    public Project updateProject(Long id, Project projectDetails) {
        return projectRepository.findById(id)
                .map(existingProject -> {
                    existingProject.setTitle(projectDetails.getTitle());
                    existingProject.setDescription(projectDetails.getDescription());
                    existingProject.setDetails(projectDetails.getDetails());
                    existingProject.setTechStack(projectDetails.getTechStack());
                    existingProject.setLink(projectDetails.getLink());
                    existingProject.setExplanation(projectDetails.getExplanation());
                    return projectRepository.save(existingProject);
                })
                .orElseThrow(() -> new RuntimeException("Project not found with ID: " + id));
    }

    // 5. Delete a project by ID
    public void deleteProject(Long id) {
        if (projectRepository.existsById(id)) {
            projectRepository.deleteById(id);
        } else {
            throw new RuntimeException("Project not found with ID: " + id);
        }
    }
}
