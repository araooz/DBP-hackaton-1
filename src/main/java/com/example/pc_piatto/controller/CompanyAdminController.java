package com.example.pc_piatto.controller;
import com.example.pc_piatto.dto.CompanyAdminDto;
import com.example.pc_piatto.service.CompanyAdminService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin/admins")
public class CompanyAdminController {
    @Autowired private CompanyAdminService service;

    @PostMapping
    public CompanyAdminDto create(@Valid @RequestBody CompanyAdminDto dto) {
        return service.create(dto);
    }
    @GetMapping
    public List<CompanyAdminDto> list() { return service.getAll(); }
    @GetMapping("/{id}")
    public CompanyAdminDto get(@PathVariable Long id) { return service.getById(id); }
    @PutMapping("/{id}")
    public CompanyAdminDto update(@PathVariable Long id, @Valid @RequestBody CompanyAdminDto dto) {
        return service.update(id, dto);
    }
}