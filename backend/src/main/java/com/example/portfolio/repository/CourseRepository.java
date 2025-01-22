package com.example.portfolio.repository;

import com.example.portfolio.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    // Find courses by the associated user's ID
    List<Course> findByUser_Id(Long userId);
}
