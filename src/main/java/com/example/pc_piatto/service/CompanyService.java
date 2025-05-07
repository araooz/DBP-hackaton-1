package com.example.pc_piatto.service;

import com.example.pc_piatto.dto.CompanyDto;
import java.util.List;

public interface CompanyService {
    CompanyDto create(CompanyDto dto);
    CompanyDto getById(Long id);
    List<CompanyDto> getAll();
    CompanyDto update(Long id, CompanyDto dto);
    void toggleStatus(Long id, boolean active);
    List<CompanyDto> getConsumptionReport(Long companyId);
}