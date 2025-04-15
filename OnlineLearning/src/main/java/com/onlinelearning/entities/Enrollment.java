package com.onlinelearning.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime enrollmentDate = LocalDateTime.now();

    @Min(0) @Max(100)
    private Integer progress = 0;

    @ManyToOne
    private User users;

    @ManyToOne
    private Course course;

}
