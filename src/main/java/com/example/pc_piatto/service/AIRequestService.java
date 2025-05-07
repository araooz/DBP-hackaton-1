package com.example.pc_piatto.service;

import com.example.pc_piatto.dto.AIRequestDto;
import java.util.List;

public interface AIRequestService {
    AIRequestDto chat(AIRequestDto dto);
    List<AIRequestDto> getHistory(Long userId);
}
