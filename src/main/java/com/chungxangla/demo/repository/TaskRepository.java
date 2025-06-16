package com.chungxangla.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chungxangla.demo.model.Task;

public interface TaskRepository extends JpaRepository<Task,Long> {
    
}
