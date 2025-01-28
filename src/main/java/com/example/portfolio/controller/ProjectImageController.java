package com.example.portfolio.controller;

import com.example.portfolio.model.ProjectImage;
import com.example.portfolio.service.ProjectImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project-images")
public class ProjectImageController {

    private final ProjectImageService projectImageService;

    @Autowired
    public ProjectImageController(ProjectImageService projectImageService) {
        this.projectImageService = projectImageService;
    }

    // Endpoint to fetch all images for a specific project
    @GetMapping("/{projectId}")
    public ResponseEntity<List<ProjectImage>> getImagesByProjectId(@PathVariable Long projectId) {
        List<ProjectImage> images = projectImageService.getImagesByProjectId(projectId);
        return ResponseEntity.ok(images);
    }
}
