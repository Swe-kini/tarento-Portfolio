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

    // 1. Save a new project or update an existing one
    public Project saveProject(Project project) {
        // Additional logic can be added here, like validating data before saving
        return projectRepository.save(project);  // Save the project to the database
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
    public Project updateProject(Long id, Project updatedProject) {
        Optional<Project> existingProjectOpt = projectRepository.findById(id);
        if (existingProjectOpt.isPresent()) {
            Project existingProject = existingProjectOpt.get();
            existingProject.setTitle(updatedProject.getTitle());
            existingProject.setDescription(updatedProject.getDescription());
            existingProject.setDetails(updatedProject.getDetails());
            existingProject.setTechStack(updatedProject.getTechStack());
            existingProject.setLink(updatedProject.getLink());
            existingProject.setExplanation(updatedProject.getExplanation());
            existingProject.setImage(updatedProject.getImage());  // Update image if provided

            return projectRepository.save(existingProject);
        } else {
            return null; // Return null if project doesn't exist
        }
    }

    // 5. Delete a project by ID (clear image if necessary)
    public boolean deleteProject(Long id) {
        if (projectRepository.existsById(id)) {
            projectRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
