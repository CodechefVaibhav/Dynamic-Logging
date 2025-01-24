package com.kashyap.starterproject.controller;

import com.kashyap.starterproject.entity.Student;
import com.kashyap.starterproject.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public Student save(@RequestBody Student student) {
        return studentService.save(student);
    }

    @GetMapping("/{id}")
    public Student findById(@PathVariable int id) {
        return studentService.findById(id).orElse(null);
    }
}
