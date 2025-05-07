package com.example.pc_piatto.service;

import com.example.pc_piatto.dto.CompanyDto;
import com.example.pc_piatto.dto.CompanyAdminDto;
import com.example.pc_piatto.entity.Company;
import com.example.pc_piatto.entity.CompanyAdmin;
import com.example.pc_piatto.repository.CompanyRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository repo;

    private CompanyDto toDto(Company c) {
        CompanyAdmin ca = c.getAdmin();
        CompanyAdminDto caDto = ca == null ? null : CompanyAdminDto.builder()
                .id(ca.getId())
                .name(ca.getName())
                .email(ca.getEmail())
                .build();
        return CompanyDto.builder()
                .id(c.getId())
                .name(c.getName())
                .ruc(c.getRuc())
                .affiliationDate(c.getAffiliationDate())
                .active(c.isActive())
                .admin(caDto)
                .build();
    }

    private Company toEntity(CompanyDto dto) {
        CompanyAdmin ca = dto.getAdmin() == null ? null : CompanyAdmin.builder()
                .id(dto.getAdmin().getId())
                .name(dto.getAdmin().getName())
                .email(dto.getAdmin().getEmail())
                .password(dto.getAdmin().getPassword())
                .build();
        return Company.builder()
                .id(dto.getId())
                .name(dto.getName())
                .ruc(dto.getRuc())
                .affiliationDate(dto.getAffiliationDate())
                .active(dto.isActive())
                .admin(ca)
                .build();
    }

    @Override
    public CompanyDto create(CompanyDto dto) {
        Company saved = repo.save(toEntity(dto));
        return toDto(saved);
    }

    @Override
    public CompanyDto getById(Long id) {
        return repo.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Company not found"));
    }

    @Override
    public List<CompanyDto> getAll() {
        return repo.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CompanyDto update(Long id, CompanyDto dto) {
        Company existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));
        existing.setName(dto.getName());
        existing.setRuc(dto.getRuc());
        existing.setAffiliationDate(dto.getAffiliationDate());
        existing.setActive(dto.isActive());
        if (dto.getAdmin() != null) {
            existing.setAdmin(toEntity(dto).getAdmin());
        }
        Company updated = repo.save(existing);
        return toDto(updated);
    }

    @Override
    public void toggleStatus(Long id, boolean active) {
        Company existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));
        existing.setActive(active);
        repo.save(existing);
    }

    @Override
    public List<CompanyDto> getConsumptionReport(Long companyId) {
        // Placeholder: real implementation would aggregate usage
        return List.of(getById(companyId));
    }
}