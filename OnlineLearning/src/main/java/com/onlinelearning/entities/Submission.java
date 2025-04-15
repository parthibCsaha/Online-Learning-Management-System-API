package com.onlinelearning.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime submittedAt = LocalDateTime.now();

    private String fileUrl;

    private Double grade;

    @ManyToOne
    private Assignment assignment;

    @ManyToOne
    private User users;

}
