package com.onlinelearning.controllers;

import com.onlinelearning.entities.Submission;
import com.onlinelearning.entities.User;
import com.onlinelearning.repo.UserRepository;
import com.onlinelearning.repo.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/assignments/{assignmentId}/submissions")
public class SubmissionController {

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Submission> submitAssignment(@AuthenticationPrincipal UserDetails ud,
                                                       @Valid @RequestBody Submission submission,
                                                       @PathVariable String assignmentId) {
        User u = userRepository.findByUsername(ud.getUsername()).orElseThrow();
        submission.setUsers(u);
        return ResponseEntity.ok(submissionRepository.save(submission));
    }

    @GetMapping
    public ResponseEntity<List<Submission>> getSubmissions(@PathVariable Long assignmentId) {
        return ResponseEntity.ok(submissionRepository.findByAssignmentId(assignmentId));
    }
}
