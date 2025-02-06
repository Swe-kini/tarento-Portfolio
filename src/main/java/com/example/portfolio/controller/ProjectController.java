package com.example.portfolio.controller;

import com.example.portfolio.model.Project;
import com.example.portfolio.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")  
@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    
    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }
     
   
    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
public ResponseEntity<Project> updateProject(
    @PathVariable Long id,
    @RequestParam("title") String title,
    @RequestParam("description") String description,
    @RequestParam("details") String details,
    @RequestParam("techStack") String techStack,
    @RequestParam("link") String link,
    @RequestParam(value = "image", required = false) MultipartFile image,
    @RequestParam("explanation") String explanation) {

    try {
        // Retrieve the existing project by ID
        Project existingProject = projectService.getProjectById(id)
            .orElseThrow(() -> new RuntimeException("Project not found"));

        // Update project details
        existingProject.setTitle(title);
        existingProject.setDescription(description);
        existingProject.setDetails(details);
        existingProject.setTechStack(techStack);
        existingProject.setLink(link);
        existingProject.setExplanation(explanation);

        // If a new image is provided, update the image
        if (image != null && !image.isEmpty()) {
            byte[] imageBytes = image.getBytes();
            existingProject.setImage(imageBytes);
        }

        // Save the updated project
        Project updatedProject = projectService.saveProject(existingProject);

        return ResponseEntity.ok(updatedProject);

    } catch (IOException e) {
        return ResponseEntity.internalServerError().build();
    } catch (RuntimeException e) {
        return ResponseEntity.notFound().build();
    }
}

@PostMapping
public ResponseEntity<Project> createProject(
    @RequestParam("title") String title,
    @RequestParam("description") String description,
    @RequestParam("details") String details,
    @RequestParam("techStack") String techStack,
    @RequestParam("link") String link,
    @RequestParam(value = "image", required = false) MultipartFile image, // Accept image
    @RequestParam("explanation") String explanation) {

    try {
        // Convert MultipartFile (image) to byte array if image is present
        byte[] imageBytes = image != null ? image.getBytes() : null;

        // Create a new project
        Project project = new Project();
        project.setTitle(title);
        project.setDescription(description);
        project.setDetails(details);
        project.setTechStack(techStack);
        project.setLink(link);
        project.setExplanation(explanation);
        project.setImage(imageBytes); // Set the image bytes if present

        // Save the new project
        Project createdProject = projectService.saveProject(project);

        return ResponseEntity.ok(createdProject);
    } catch (IOException e) {
        return ResponseEntity.internalServerError().build();
    }
}

    @DeleteMapping("/{id}")
public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
    try {
    
        projectService.deleteProject(id);

        return ResponseEntity.noContent().build();
    } catch (RuntimeException e) {
        return ResponseEntity.notFound().build();
    }
}

}
