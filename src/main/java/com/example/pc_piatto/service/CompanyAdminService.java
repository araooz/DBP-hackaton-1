package com.example.pc_piatto.service;

import com.example.pc_piatto.dto.CompanyAdminDto;
import java.util.List;

public interface CompanyAdminService {
    CompanyAdminDto create(CompanyAdminDto dto);
    CompanyAdminDto getById(Long id);
    List<CompanyAdminDto> getAll();
    CompanyAdminDto update(Long id, CompanyAdminDto dto);
}