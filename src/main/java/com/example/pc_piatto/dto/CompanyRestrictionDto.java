package com.example.pc_piatto.dto;

import lombok.*;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyRestrictionDto {
    private Long id;

    @NotBlank
    private String modelName;

    @Min(0)
    private int maxTokens;

    private Long companyId;
}