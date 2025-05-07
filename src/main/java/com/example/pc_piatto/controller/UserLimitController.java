package com.example.pc_piatto.controller;
import com.example.pc_piatto.dto.UserLimitDto;
import com.example.pc_piatto.service.UserLimitService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/company/users/{userId}/limits")
public class UserLimitController {
    @Autowired private UserLimitService service;

    @PostMapping
    public UserLimitDto create(@PathVariable Long userId, @Valid @RequestBody UserLimitDto dto) {
        dto.setUserId(userId);
        return service.create(dto);
    }
    @GetMapping
    public List<UserLimitDto> list() { return service.getAll(); }
    @PutMapping("/{id}")
    public UserLimitDto update(@PathVariable Long userId, @PathVariable Long id, @Valid @RequestBody UserLimitDto dto) {
        dto.setUserId(userId);
        return service.update(id, dto);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.delete(id); }
}