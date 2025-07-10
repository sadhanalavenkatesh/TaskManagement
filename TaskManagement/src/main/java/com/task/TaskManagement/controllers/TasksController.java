package com.task.TaskManagement.controllers;


import com.task.TaskManagement.Entity.TasksEntity;
import com.task.TaskManagement.services.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
    public class TasksController {

@Autowired
private TasksService taskService;

    @PostMapping
        public ResponseEntity<TasksEntity> createTask(@RequestBody TasksEntity task) {
            return ResponseEntity.ok(taskService.createTask(task));
        }

        @PutMapping("/{id}")
        public ResponseEntity<TasksEntity> updateTask(@PathVariable Integer id, @RequestBody TasksEntity task) {
            return ResponseEntity.ok(taskService.updateTask(id, task));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteTask(@PathVariable Integer id) {
            taskService.deleteTask(id);
            return ResponseEntity.noContent().build();
        }

        @GetMapping("/{id}")
        public ResponseEntity<TasksEntity> getTaskById(@PathVariable Integer id) {
            return ResponseEntity.ok(taskService.getTaskById(id));
        }

        @GetMapping
        public ResponseEntity<List<TasksEntity>> getAllTasks() {
            return ResponseEntity.ok(taskService.getAllTasks());
        }
    }


