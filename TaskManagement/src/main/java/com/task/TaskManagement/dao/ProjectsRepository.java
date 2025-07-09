package com.task.TaskManagement.dao;

import com.task.TaskManagement.Entity.ProjectsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectsRepository extends JpaRepository<ProjectsEntity,Integer>{
}
