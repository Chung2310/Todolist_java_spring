package com.chungxangla.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chungxangla.demo.model.Task;
import com.chungxangla.demo.service.TaskService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public List<Task> getAll() {
        return taskService.getAll();
    }

    @PostMapping
    public Task add(@RequestBody Task task) {
        System.out.println("Đang thêm task: " + task);
        return taskService.add(task);
    }
    
    @PutMapping("/{id}/toggle")
    public Task toggle(@PathVariable Long id) {
        
        return taskService.toggleComplete(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        taskService.delete(id);
    }
    
}
