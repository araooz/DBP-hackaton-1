package com.example.pc_piatto.service;

import com.example.pc_piatto.dto.UserDto;
import com.example.pc_piatto.dto.UserLimitDto;
import com.example.pc_piatto.entity.User;
import com.example.pc_piatto.entity.UserLimit;
import com.example.pc_piatto.entity.Company;
import com.example.pc_piatto.repository.UserRepository;
import com.example.pc_piatto.repository.UserLimitRepository;
import com.example.pc_piatto.repository.CompanyRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired private UserRepository userRepo;
    @Autowired private CompanyRepository companyRepo;
    @Autowired private UserLimitRepository limitRepo;

    private UserDto toDto(User u) {
        List<UserLimitDto> limits = u.getLimits().stream()
                .map(l -> UserLimitDto.builder()
                        .id(l.getId())
                        .resource(l.getResource())
                        .limit(l.getLimit())
                        .userId(u.getId())
                        .build())
                .collect(Collectors.toList());
        return UserDto.builder()
                .id(u.getId())
                .username(u.getUsername())
                .email(u.getEmail())
                .companyId(u.getCompany() != null ? u.getCompany().getId() : null)
                .limits(limits)
                .build();
    }

    private User toEntity(UserDto dto) {
        Company c = dto.getCompanyId() != null ? companyRepo.findById(dto.getCompanyId()).orElse(null) : null;
        return User.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .company(c)
                .build();
    }

    @Override
    public UserDto create(UserDto dto) {
        User u = toEntity(dto);
        User saved = userRepo.save(u);
        return toDto(saved);
    }

    @Override
    public UserDto getById(Long id) {
        return userRepo.findById(id).map(this::toDto)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<UserDto> getAll() {
        return userRepo.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public UserDto update(Long id, UserDto dto) {
        User existing = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        existing.setUsername(dto.getUsername());
        existing.setEmail(dto.getEmail());
        if (dto.getCompanyId() != null) {
            Company c = companyRepo.findById(dto.getCompanyId()).orElseThrow(() -> new RuntimeException("Company not found"));
            existing.setCompany(c);
        }
        User updated = userRepo.save(existing);
        return toDto(updated);
    }
}