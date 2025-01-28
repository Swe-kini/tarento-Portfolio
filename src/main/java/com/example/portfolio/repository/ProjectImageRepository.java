package com.example.portfolio.repository;

import com.example.portfolio.model.ProjectImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectImageRepository extends JpaRepository<ProjectImage, Long> {

    // Fetch all images for a specific project
    List<ProjectImage> findByProjectId(Long projectId);
}
