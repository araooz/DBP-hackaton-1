
package com.example.pc_piatto.controller;
import com.example.pc_piatto.dto.CompanyDto;
import com.example.pc_piatto.service.CompanyService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin/companies")
public class CompanyController {
    @Autowired private CompanyService service;

    @PostMapping
    public CompanyDto create(@Valid @RequestBody CompanyDto dto) {
        return service.create(dto);
    }
    @GetMapping
    public List<CompanyDto> list() { return service.getAll(); }
    @GetMapping("/{id}")
    public CompanyDto get(@PathVariable Long id) { return service.getById(id); }
    @PutMapping("/{id}")
    public CompanyDto update(@PathVariable Long id, @Valid @RequestBody CompanyDto dto) {
        return service.update(id, dto);
    }
    @PatchMapping("/{id}/status")
    public void toggle(@PathVariable Long id, @RequestParam boolean active) {
        service.toggleStatus(id, active);
    }
}
