package com.example.pc_piatto.service;

import com.example.pc_piatto.dto.CompanyRestrictionDto;
import com.example.pc_piatto.entity.CompanyRestriction;
import com.example.pc_piatto.repository.CompanyRestrictionRepository;
import com.example.pc_piatto.repository.CompanyRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyRestrictionServiceImpl implements CompanyRestrictionService {
    @Autowired private CompanyRestrictionRepository repo;
    @Autowired private CompanyRepository companyRepo;

    private CompanyRestrictionDto toDto(CompanyRestriction r) {
        return CompanyRestrictionDto.builder()
                .id(r.getId())
                .modelName(r.getModelName())
                .maxTokens(r.getMaxTokens())
                .companyId(r.getCompany()!=null?r.getCompany().getId():null)
                .build();
    }

    private CompanyRestriction toEntity(CompanyRestrictionDto dto) {
        return CompanyRestriction.builder()
                .id(dto.getId())
                .modelName(dto.getModelName())
                .maxTokens(dto.getMaxTokens())
                .company(companyRepo.findById(dto.getCompanyId()).orElse(null))
                .build();
    }

    @Override
    public CompanyRestrictionDto create(CompanyRestrictionDto dto) {
        CompanyRestriction saved = repo.save(toEntity(dto));
        return toDto(saved);
    }

    @Override
    public CompanyRestrictionDto getById(Long id) {
        return repo.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Restriction not found"));
    }

    @Override
    public List<CompanyRestrictionDto> getAll() {
        return repo.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public CompanyRestrictionDto update(Long id, CompanyRestrictionDto dto) {
        CompanyRestriction existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Restriction not found"));
        existing.setModelName(dto.getModelName());
        existing.setMaxTokens(dto.getMaxTokens());
        CompanyRestriction updated = repo.save(existing);
        return toDto(updated);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}