package com.kashyap.starterproject.controller;

import com.kashyap.starterproject.entity.Department;
import com.kashyap.starterproject.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public Department saveToDatabase(@RequestBody Department department) {
        return departmentService.saveToDatabase(department);
    }

    @GetMapping("/{id}")
    public Department findFromDatabase(@PathVariable int id) {
        List<String> s = new ArrayList<>();
        s.add("one");
        System.out.println(s);
        Department d =  departmentService.findFromDatabase(id,s).orElse(null);
        System.out.println(s);
        return d;
    }

    @PostMapping("/redis")
    public String saveToRedis(@RequestBody Department department) {
        departmentService.saveToRedis(department);
        return "Department saved to Redis.";
    }

    @GetMapping("/redis/{id}")
    public Department findFromRedis(@PathVariable int id) {
        return departmentService.findFromRedis(id);
    }
}
