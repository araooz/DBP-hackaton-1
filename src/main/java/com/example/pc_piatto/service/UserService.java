package com.example.pc_piatto.service;

import com.example.pc_piatto.dto.UserDto;
import java.util.List;

public interface UserService {
    UserDto create(UserDto dto);
    UserDto getById(Long id);
    List<UserDto> getAll();
    UserDto update(Long id, UserDto dto);
}