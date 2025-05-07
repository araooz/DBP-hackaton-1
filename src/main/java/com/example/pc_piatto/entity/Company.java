package com.example.pc_piatto.entity;

import jakarta.persistence.*;

import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Size(min = 11, max = 11)
    private String ruc;

    @NotNull
    private LocalDate affiliationDate;

    private boolean active;

    @OneToOne(cascade = CascadeType.ALL)
    private CompanyAdmin admin;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<User> users;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<CompanyRestriction> restrictions;
}