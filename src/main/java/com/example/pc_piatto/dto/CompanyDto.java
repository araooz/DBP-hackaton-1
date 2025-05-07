package com.example.pc_piatto.dto;

import lombok.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyDto {
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

    private CompanyAdminDto admin;
}