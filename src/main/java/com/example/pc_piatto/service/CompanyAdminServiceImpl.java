package com.example.pc_piatto.service;

import com.example.pc_piatto.dto.CompanyAdminDto;
import com.example.pc_piatto.entity.CompanyAdmin;
import com.example.pc_piatto.repository.CompanyAdminRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyAdminServiceImpl implements CompanyAdminService {
    @Autowired
    private CompanyAdminRepository repo;

    private CompanyAdminDto toDto(CompanyAdmin ca) {
        return CompanyAdminDto.builder()
                .id(ca.getId())
                .name(ca.getName())
                .email(ca.getEmail())
                .build();
    }

    private CompanyAdmin toEntity(CompanyAdminDto dto) {
        return CompanyAdmin.builder()
                .id(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
    }

    @Override
    public CompanyAdminDto create(CompanyAdminDto dto) {
        CompanyAdmin saved = repo.save(toEntity(dto));
        return toDto(saved);
    }

    @Override
    public CompanyAdminDto getById(Long id) {
        return repo.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
    }

    @Override
    public List<CompanyAdminDto> getAll() {
        return repo.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CompanyAdminDto update(Long id, CompanyAdminDto dto) {
        CompanyAdmin existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());
        CompanyAdmin updated = repo.save(existing);
        return toDto(updated);
    }
}