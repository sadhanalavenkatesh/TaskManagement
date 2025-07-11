package com.task.TaskManagement.controllers;


import com.task.TaskManagement.Entity.ProjectsEntity;
import com.task.TaskManagement.Entity.TasksEntity;
import com.task.TaskManagement.dto.ProjectsDto;
import com.task.TaskManagement.dto.ResponseWrapper;
import com.task.TaskManagement.dto.TasksDto;
import com.task.TaskManagement.services.TasksService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
    public class TasksController {

@Autowired
private TasksService taskService;
    @PostMapping
    public ResponseEntity<ResponseWrapper<TasksEntity>> createTask(@Valid @RequestBody TasksDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.createTask(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseWrapper<TasksEntity>> updateTask(@PathVariable Integer id, @RequestBody TasksDto dto) {
        return ResponseEntity.ok(taskService.updateTask(id, dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<String>> deleteTask(@PathVariable Integer id) {
        return ResponseEntity.ok(taskService.deleteTask(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseWrapper<TasksEntity>> getProjectById(@PathVariable Integer id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper<List<TasksEntity>>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    }


