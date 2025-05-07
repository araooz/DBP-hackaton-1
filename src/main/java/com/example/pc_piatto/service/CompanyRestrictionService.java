package com.example.pc_piatto.service;

import com.example.pc_piatto.dto.CompanyRestrictionDto;
import java.util.List;

public interface CompanyRestrictionService {
    CompanyRestrictionDto create(CompanyRestrictionDto dto);
    CompanyRestrictionDto getById(Long id);
    List<CompanyRestrictionDto> getAll();
    CompanyRestrictionDto update(Long id, CompanyRestrictionDto dto);
    void delete(Long id);
}
