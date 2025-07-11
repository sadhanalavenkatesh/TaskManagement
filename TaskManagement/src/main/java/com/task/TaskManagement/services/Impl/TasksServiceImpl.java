package com.task.TaskManagement.services.Impl;


import com.task.TaskManagement.Entity.ProjectsEntity;
import com.task.TaskManagement.Entity.TasksEntity;
import com.task.TaskManagement.dao.ProjectsRepository;
import com.task.TaskManagement.dao.TasksRepository;
import com.task.TaskManagement.dto.ResponseWrapper;
import com.task.TaskManagement.dto.TasksDto;
import com.task.TaskManagement.services.TasksService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TasksServiceImpl implements TasksService {
    @Autowired
    private TasksRepository taskRepository;
    @Autowired
    private ProjectsRepository projectRepository;

    @Override
    public ResponseWrapper<TasksEntity> createTask(TasksDto dto) {
        ProjectsEntity project = projectRepository.findById(dto.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + dto.getProjectId()));

        TasksEntity task= new TasksEntity();
        BeanUtils.copyProperties(dto, task); // Automatically maps matching fields
        task.setProject(project);
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());

        TasksEntity saved = taskRepository.save(task);
        return new ResponseWrapper<>("task created successfully", null);
    }

    @Override
    public ResponseWrapper<TasksEntity> updateTask(Integer id, TasksDto dto) {
        TasksEntity existing = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("task not found"));

        ProjectsEntity project = projectRepository.findById(dto.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + dto.getProjectId()));

        BeanUtils.copyProperties(dto, existing,"taskId"); // Copy all matching fields from DTO
        existing.setProject(project);
        existing.setUpdatedAt(LocalDateTime.now());

        TasksEntity updated =taskRepository.save(existing);
        return new ResponseWrapper<>("task updated successfully", null);
    }

    @Override
    public ResponseWrapper<String> deleteTask(Integer id) {
        TasksEntity task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("task not found"));

        task.setIsDeleted(true);  // Mark as deleted
        taskRepository.save(task); // Save the updated project

        return new ResponseWrapper<>("task deleted successfully",null);
    }

    @Override
    public ResponseWrapper<TasksEntity> getTaskById(Integer id) {
        TasksEntity task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("task not found"));
        return new ResponseWrapper<>("task fetched successfully", task);
    }

    @Override
    public ResponseWrapper<List<TasksEntity>> getAllTasks() {
        List<TasksEntity> list = taskRepository.findAll();
        return new ResponseWrapper<>("All Tasks fetched successfully", list);

    }
}
