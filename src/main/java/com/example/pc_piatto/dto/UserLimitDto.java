package com.example.pc_piatto.dto;

import lombok.*;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLimitDto {
    private Long id;

    @NotBlank
    private String resource;

    @Min(0)
    private int limit;

    private Long userId;
}