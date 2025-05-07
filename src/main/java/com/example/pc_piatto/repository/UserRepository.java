package com.example.pc_piatto.repository;

import com.example.pc_piatto.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}