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
    public List<Course> getCoursesByUserId(Long userId) {
        return courseRepository.findByUser_Id(userId);
    }

    // Add a new course
    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    // Update an existing course
    public Course updateCourse(Long id, Course updatedCourse) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        course.setName(updatedCourse.getName());
        course.setProvider(updatedCourse.getProvider());
        course.setCompletionDate(updatedCourse.getCompletionDate());
        return courseRepository.save(course);
    }

    // Delete a course by ID
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}
