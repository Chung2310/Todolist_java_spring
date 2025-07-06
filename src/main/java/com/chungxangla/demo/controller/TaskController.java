package com.chungxangla.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chungxangla.demo.model.ApiResponse;
import com.chungxangla.demo.model.Task;
import com.chungxangla.demo.service.TaskService;

import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ApiResponse<List<Task>>> getAll() {
        List<Task> tasks = taskService.getAll();
        return ResponseEntity.ok(new ApiResponse<>(true, "Lấy dữ liệu thành công", tasks));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Task>> add(@RequestBody Task task) {
        Task created = taskService.add(task);
        return ResponseEntity.created(URI.create("/api/tasks"+ created.getId()))
        .body(new ApiResponse<>(true, "Thêm sản phẩm thành công", created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Task>> update(@PathVariable Long id, @RequestBody Task updatedTask) {
        Task task = taskService.update(id, updatedTask);
        return ResponseEntity.ok(new ApiResponse<>(true, "Cập nhật thành công", task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id){
        taskService.delete(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Xoá thành công", null));
    }
    
    
}
