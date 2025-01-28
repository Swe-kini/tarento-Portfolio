package com.example.portfolio.controller;

import com.example.portfolio.model.Course;
import com.example.portfolio.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5174")  // Allow requests from your frontend
@RestController
@RequestMapping("/api/courses")  // Base path for this controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    // Get all courses
    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    // Get a specific course by ID
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create a new course
    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        Course createdCourse = courseService.createCourse(course);
        return ResponseEntity.status(201).body(createdCourse); // HTTP 201 Created
    }

    // Update an existing course
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course course) {
        return courseService.updateCourse(id, course)
                .map(updatedCourse -> ResponseEntity.ok().body(updatedCourse))
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete a course
    @DeleteMapping("/{id}")
public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
    if (courseService.deleteCourse(id)) {
        return ResponseEntity.ok("Deleted successfully"); // HTTP 200 OK with success message
    }
    return ResponseEntity.notFound().build(); // HTTP 404 Not Found
}

}
