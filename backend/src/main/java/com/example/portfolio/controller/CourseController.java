package com.example.portfolio.controller;

import com.example.portfolio.model.Course;
import com.example.portfolio.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // Get all courses for a specific user
    @GetMapping("/user/{userId}")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

}
