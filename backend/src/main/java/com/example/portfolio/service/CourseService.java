package com.example.portfolio.service;

import com.example.portfolio.model.Course;
import com.example.portfolio.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    // Get all courses for a specific user
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}
