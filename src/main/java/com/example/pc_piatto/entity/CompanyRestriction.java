package com.example.pc_piatto.entity;

import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyRestriction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String modelName;

    @Min(0)
    private int maxTokens;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}