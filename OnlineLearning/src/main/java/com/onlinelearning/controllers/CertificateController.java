package com.onlinelearning.controllers;

import com.onlinelearning.entities.Certificate;
import com.onlinelearning.repo.CertificateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/certificates")
public class CertificateController {

    @Autowired
    private CertificateRepository certificateRepository;

    @GetMapping("/{enrollmentId}")
    public ResponseEntity<Certificate> getCertificate(@PathVariable Long enrollmentId) {
        return certificateRepository
                .findById(enrollmentId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
