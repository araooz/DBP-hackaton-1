package com.example.pc_piatto.controller;
import com.example.pc_piatto.dto.CompanyRestrictionDto;
import com.example.pc_piatto.service.CompanyRestrictionService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/company/restrictions")
public class CompanyRestrictionController {
    @Autowired private CompanyRestrictionService service;

    @PostMapping
    public CompanyRestrictionDto create(@Valid @RequestBody CompanyRestrictionDto dto) {
        return service.create(dto);
    }
    @GetMapping
    public List<CompanyRestrictionDto> list() { return service.getAll(); }
    @PutMapping("/{id}")
    public CompanyRestrictionDto update(@PathVariable Long id, @Valid @RequestBody CompanyRestrictionDto dto) {
        return service.update(id, dto);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.delete(id); }
}