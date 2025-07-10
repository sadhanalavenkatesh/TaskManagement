package com.task.TaskManagement.services;

import com.task.TaskManagement.Entity.TasksEntity;

import java.util.List;

public interface TasksService {
    TasksEntity createTask(TasksEntity task);
    TasksEntity updateTask(Integer id, TasksEntity task);
    void deleteTask(Integer id);
    TasksEntity getTaskById(Integer id);
    List<TasksEntity> getAllTasks();
}
