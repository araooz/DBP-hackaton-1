package com.example.pc_piatto.repository;

import com.example.pc_piatto.entity.UserLimit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLimitRepository extends JpaRepository<UserLimit, Long> {
}