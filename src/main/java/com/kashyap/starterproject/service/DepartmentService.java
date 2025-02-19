package com.kashyap.starterproject.service;

import com.kashyap.starterproject.entity.Department;
import com.kashyap.starterproject.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    private static final String REDIS_KEY = "Department";
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentService.class);

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private RedisTemplate<String, Department> redisTemplate;

    public Department saveToDatabase(Department department) {
        LOGGER.info("Saving department to database: {}", department);
        return departmentRepository.save(department);
    }

    public Optional<Department> findFromDatabase(int id, List<String> s) {
        LOGGER.debug("DEBUG VERSION : Fetching department from database with ID: {}", id);
        LOGGER.info("INFO VERSION : Fetching department from database with ID: {}", id);
        s.add("two");
        return departmentRepository.findById(id);
    }

    public void saveToRedis(Department department) {
        LOGGER.info("Saving department to Redis: {}", department);
        redisTemplate.opsForHash().put(REDIS_KEY, department.getId(), department);
    }

    public Department findFromRedis(int id) {
        LOGGER.info("INFO VERSION : Fetching department from Redis with ID: {}", id);
        LOGGER.debug("DEBUG VERSION : Fetching department from Redis with ID: {}", id);
        return (Department) redisTemplate.opsForHash().get(REDIS_KEY, id);
    }
}
