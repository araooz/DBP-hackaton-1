package com.example.pc_piatto.repository;

import com.example.pc_piatto.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}