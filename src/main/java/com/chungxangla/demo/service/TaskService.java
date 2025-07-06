package com.chungxangla.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chungxangla.demo.model.Task;
import com.chungxangla.demo.repository.TaskRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public List<Task> getAll(){
        return taskRepository.findAll();
    }

    public Task add(Task task){
        return taskRepository.save(task);
    }

    public Task toggleComplete(Long id){
        Task task = taskRepository.findById(id).orElseThrow();
        task.setCompleted( !task.getCompleted());
        return taskRepository.save(task);
    }

    public void delete(Long id){
        taskRepository.deleteById(id);;
    }

    public Task update(Long id, Task updatedTask) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy task"));

        existingTask.setTitle(updatedTask.getTitle());
        return taskRepository.save(existingTask);
    }
}
