package com.onlinelearning.controllers;

import com.onlinelearning.entities.Enrollment;
import com.onlinelearning.entities.User;
import com.onlinelearning.repo.EnrollmentRepository;
import com.onlinelearning.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Enrollment> enroll(@AuthenticationPrincipal UserDetails ud, @RequestBody Enrollment enrollment) {
        User u = userRepository.findByUsername(ud.getUsername()).orElseThrow();
        enrollment.setUsers(u);
        return ResponseEntity.ok(enrollmentRepository.save(enrollment));
    }

    @GetMapping
    public ResponseEntity<List<Enrollment>> getEnrollments(@AuthenticationPrincipal UserDetails ud) {
        User u = userRepository.findByUsername(ud.getUsername()).orElseThrow();
        return ResponseEntity.ok(enrollmentRepository.findByUsersId(u.getId()));
    }


}