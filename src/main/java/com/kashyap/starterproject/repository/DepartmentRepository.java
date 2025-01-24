package com.kashyap.starterproject.repository;

import com.kashyap.starterproject.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
