package com.luxmed.company.repository;

import com.luxmed.company.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Department, Long> {
}