package com.onlinelearning.controllers;

import com.onlinelearning.dtos.AuthRequest;
import com.onlinelearning.dtos.AuthResponse;
import com.onlinelearning.entities.User;
import com.onlinelearning.repo.UserRepository;
import com.onlinelearning.security.CustomUserDetailsService;
import com.onlinelearning.security.JWTUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username is already in use");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("STUDENT");
        return ResponseEntity.ok(userRepository.save(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(authRequest.getUsername());
        String token = jwtUtil.generate(userDetails.getUsername());
        return ResponseEntity.ok(new AuthResponse(token));
    }


}











