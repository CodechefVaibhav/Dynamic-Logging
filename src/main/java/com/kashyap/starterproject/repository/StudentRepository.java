package com.kashyap.starterproject.repository;

import com.kashyap.starterproject.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
