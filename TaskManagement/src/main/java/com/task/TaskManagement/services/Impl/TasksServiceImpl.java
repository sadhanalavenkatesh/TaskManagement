package com.task.TaskManagement.services.Impl;

import com.task.TaskManagement.Entity.ClientEntity;
import com.task.TaskManagement.Entity.ProjectsEntity;
import com.task.TaskManagement.Entity.TasksEntity;
import com.task.TaskManagement.dao.ProjectsRepository;
import com.task.TaskManagement.dao.TasksRepository;
import com.task.TaskManagement.services.TasksService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TasksServiceImpl implements TasksService {
    @Autowired
    private TasksRepository taskRepository;
    @Autowired
    private ProjectsRepository projectRepository;
    @Override
    public TasksEntity createTask(TasksEntity task) {
        int projectId = task.getProject().getProjectId(); // only ID is coming from request
        ProjectsEntity fullProject = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + projectId));

        task.setProject(fullProject);
        return taskRepository.save(task);
    }

    @Override
    public TasksEntity updateTask(Integer id, TasksEntity task) {
        TasksEntity tasks = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));
        tasks.setTaskDate(task.getTaskDate());
        tasks.setTaskName(task.getTaskName());
        tasks.setStatus(task.getStatus());
        tasks.setPercentComplete(task.getPercentComplete());
        tasks.setRemarks(task.getRemarks());
        tasks.setProject(task.getProject());
        tasks.setUpdatedAt(task.getUpdatedAt());
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(Integer id) {
        taskRepository.deleteById(id);

    }

    @Override
    public TasksEntity getTaskById(Integer id) {
        return taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Task not found"));
    }

    @Override
    public List<TasksEntity> getAllTasks() {
        return taskRepository.findAll();
    }
}
