package com.example.pc_piatto.controller;
import com.example.pc_piatto.dto.AIRequestDto;
import com.example.pc_piatto.service.AIRequestService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/ai")
public class AIRequestController {
    @Autowired private AIRequestService service;

    @PostMapping("/chat")
    public AIRequestDto chat(@Valid @RequestBody AIRequestDto dto) {
        return service.chat(dto);
    }

    @GetMapping("/history")
    public List<AIRequestDto> history(@RequestParam Long userId) {
        return service.getHistory(userId);
    }
}