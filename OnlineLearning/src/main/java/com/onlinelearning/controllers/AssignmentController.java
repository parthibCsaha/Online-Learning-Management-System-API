package com.onlinelearning.controllers;

import com.onlinelearning.entities.Assignment;
import com.onlinelearning.repo.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/courses/{courseId}/assignments")
public class AssignmentController {

    @Autowired
    private AssignmentRepository assignmentRepository;

    @GetMapping
    public ResponseEntity<List<Assignment>> getAssignments(@PathVariable Long courseId) {
        return ResponseEntity.ok(assignmentRepository.findByCourseId(courseId));
    }

}