package com.kashyap.starterproject.service;

import com.kashyap.starterproject.entity.Student;
import com.kashyap.starterproject.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public Optional<Student> findById(int id) {
        return studentRepository.findById(id);
    }
}
