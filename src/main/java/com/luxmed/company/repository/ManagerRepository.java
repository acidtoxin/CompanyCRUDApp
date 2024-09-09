package com.luxmed.company.repository;

import com.luxmed.company.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Department, Long> {
}