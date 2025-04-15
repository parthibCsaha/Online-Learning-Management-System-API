package com.onlinelearning.controllers;

import com.onlinelearning.entities.Course;
import com.onlinelearning.repo.CourseRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/courses")
public class AdminCourseController {

    @Autowired
    private CourseRepository courseRepository;

    @PostMapping
    public ResponseEntity<Course> createCourse(@Valid @RequestBody Course course) {
        return ResponseEntity.ok(courseRepository.save(course));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course course) {
        return courseRepository.findById(id)
                .map(existing -> {
                    existing.setTitle(course.getTitle());
                    existing.setDescription(course.getDescription());
                    existing.setInstructor(course.getInstructor());
                    existing.setDuration(course.getDuration());
                    existing.setCategory(course.getCategory());
                    return ResponseEntity.ok(courseRepository.save(existing));
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
