package com.example.pc_piatto.entity;

import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLimit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String resource;

    @Min(0)
    private int limit;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}