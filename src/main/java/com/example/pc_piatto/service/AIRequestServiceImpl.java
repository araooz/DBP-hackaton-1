package com.example.pc_piatto.service;

import com.example.pc_piatto.dto.AIRequestDto;
import com.example.pc_piatto.entity.AIRequest;
import com.example.pc_piatto.entity.User;
import com.example.pc_piatto.repository.AIRequestRepository;
import com.example.pc_piatto.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

@Service
public class AIRequestServiceImpl implements AIRequestService {
    @Autowired private AIRequestRepository repo;
    @Autowired private UserRepository userRepo;

    private AIRequestDto toDto(AIRequest r) {
        return AIRequestDto.builder()
                .id(r.getId())
                .prompt(r.getPrompt())
                .response(r.getResponse())
                .tokens(r.getTokens())
                .timestamp(r.getTimestamp())
                .userId(r.getUser()!=null?r.getUser().getId():null)
                .build();
    }

    private AIRequest toEntity(AIRequestDto dto) {
        User u = userRepo.findById(dto.getUserId()).orElse(null);
        return AIRequest.builder()
                .id(dto.getId())
                .prompt(dto.getPrompt())
                .response(dto.getResponse())
                .tokens(dto.getTokens())
                .timestamp(dto.getTimestamp()!=null?dto.getTimestamp():LocalDateTime.now())
                .user(u)
                .build();
    }

    @Override
    public AIRequestDto chat(AIRequestDto dto) {
        // Aquí llamarías al SDK de IA y obtendrías respuesta y tokens
        AIRequest r = toEntity(dto);
        AIRequest saved = repo.save(r);
        return toDto(saved);
    }

    @Override
    public List<AIRequestDto> getHistory(Long userId) {
        return repo.findAll().stream()
                .filter(r -> r.getUser()!=null && r.getUser().getId().equals(userId))
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
