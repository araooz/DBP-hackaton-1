package com.example.pc_piatto.dto;

import lombok.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AIRequestDto {
    private Long id;

    @NotBlank
    private String prompt;

    @NotBlank
    private String response;

    @Min(0)
    private int tokens;

    @NotNull
    private LocalDateTime timestamp;

    private Long userId;
}