package com.onlinelearning.controllers;

import com.onlinelearning.entities.Lesson;
import com.onlinelearning.repo.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/courses/{courseId}/lessons")
public class LessonController {

    @Autowired
    private LessonRepository lessonRepository;

    @GetMapping
    public ResponseEntity<List<Lesson>> getLessons(@PathVariable Long courseId) {
        return ResponseEntity.ok(lessonRepository.findByCourseId(courseId));
    }

}