package com.example.portfolio.service;

import com.example.portfolio.model.Project;
import com.example.portfolio.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    public Project createProject(String title, String description, String details, String techStack, String link, MultipartFile image, String explanation) throws IOException {
        byte[] imageBytes = (image != null && !image.isEmpty()) ? image.getBytes() : null;

        Project project = new Project();
        project.setTitle(title);
        project.setDescription(description);
        project.setDetails(details);
        project.setTechStack(techStack);
        project.setLink(link);
        project.setExplanation(explanation);
        project.setImage(imageBytes);

        return projectRepository.save(project);
    }

    public Project updateProject(Long id, String title, String description, String details, String techStack, String link, MultipartFile image, String explanation) throws IOException {
        Optional<Project> existingProjectOpt = projectRepository.findById(id);

        if (existingProjectOpt.isPresent()) {
            Project existingProject = existingProjectOpt.get();
            existingProject.setTitle(title);
            existingProject.setDescription(description);
            existingProject.setDetails(details);
            existingProject.setTechStack(techStack);
            existingProject.setLink(link);
            existingProject.setExplanation(explanation);

            if (image != null && !image.isEmpty()) {
                existingProject.setImage(image.getBytes());
            }

            return projectRepository.save(existingProject);
        }
        return null;
    }

    public boolean deleteProject(Long id) {
        if (projectRepository.existsById(id)) {
            projectRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
