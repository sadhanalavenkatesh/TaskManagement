package com.task.TaskManagement.dao;

import com.task.TaskManagement.Entity.TasksEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TasksRepository extends JpaRepository<TasksEntity,Integer> {
}
