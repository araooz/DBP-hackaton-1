package com.example.pc_piatto.controller;
import com.example.pc_piatto.dto.UserDto;
import com.example.pc_piatto.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/company/users")
public class UserController {
    @Autowired private UserService service;

    @PostMapping
    public UserDto create(@Valid @RequestBody UserDto dto) {
        return service.create(dto);
    }
    @GetMapping
    public List<UserDto> list() { return service.getAll(); }
    @GetMapping("/{id}")
    public UserDto get(@PathVariable Long id) { return service.getById(id); }
    @PutMapping("/{id}")
    public UserDto update(@PathVariable Long id, @Valid @RequestBody UserDto dto) {
        return service.update(id, dto);
    }
}