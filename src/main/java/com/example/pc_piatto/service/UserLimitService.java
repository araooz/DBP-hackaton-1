package com.example.pc_piatto.service;

import com.example.pc_piatto.dto.UserLimitDto;
import java.util.List;

public interface UserLimitService {
    UserLimitDto create(UserLimitDto dto);
    UserLimitDto getById(Long id);
    List<UserLimitDto> getAll();
    UserLimitDto update(Long id, UserLimitDto dto);
    void delete(Long id);
}
