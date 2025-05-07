package com.example.pc_piatto.service;

import com.example.pc_piatto.dto.UserLimitDto;
import com.example.pc_piatto.entity.UserLimit;
import com.example.pc_piatto.repository.UserLimitRepository;
import com.example.pc_piatto.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserLimitServiceImpl implements UserLimitService {
    @Autowired private UserLimitRepository repo;
    @Autowired private UserRepository userRepo;

    private UserLimitDto toDto(UserLimit ul) {
        return UserLimitDto.builder()
                .id(ul.getId())
                .resource(ul.getResource())
                .limit(ul.getLimit())
                .userId(ul.getUser()!=null?ul.getUser().getId():null)
                .build();
    }

    private UserLimit toEntity(UserLimitDto dto) {
        return UserLimit.builder()
                .id(dto.getId())
                .resource(dto.getResource())
                .limit(dto.getLimit())
                .user(userRepo.findById(dto.getUserId()).orElse(null))
                .build();
    }

    @Override
    public UserLimitDto create(UserLimitDto dto) {
        UserLimit saved = repo.save(toEntity(dto));
        return toDto(saved);
    }

    @Override
    public UserLimitDto getById(Long id) {
        return repo.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("UserLimit not found"));
    }

    @Override
    public List<UserLimitDto> getAll() {
        return repo.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public UserLimitDto update(Long id, UserLimitDto dto) {
        UserLimit existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("UserLimit not found"));
        existing.setResource(dto.getResource());
        existing.setLimit(dto.getLimit());
        UserLimit updated = repo.save(existing);
        return toDto(updated);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
