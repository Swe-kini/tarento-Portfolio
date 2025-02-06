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
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Project> createProject(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("details") String details,
            @RequestParam("techStack") String techStack,
            @RequestParam("link") String link,
            @RequestParam(value = "image", required = false) MultipartFile image,
            @RequestParam("explanation") String explanation) {

        try {
            Project createdProject = projectService.createProject(title, description, details, techStack, link, image, explanation);
            return ResponseEntity.ok(createdProject);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
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
            Project updatedProject = projectService.updateProject(id, title, description, details, techStack, link, image, explanation);
            return updatedProject != null ? ResponseEntity.ok(updatedProject) : ResponseEntity.notFound().build();
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        return projectService.deleteProject(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
