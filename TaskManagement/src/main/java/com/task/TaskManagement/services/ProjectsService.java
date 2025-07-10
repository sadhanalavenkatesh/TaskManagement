package com.task.TaskManagement.services;

import com.task.TaskManagement.Entity.ProjectsEntity;
import com.task.TaskManagement.dto.ProjectsDto;
import com.task.TaskManagement.dto.ResponseWrapper;

import java.util.List;

public interface ProjectsService {
    ResponseWrapper<ProjectsEntity> createProject(ProjectsDto dto);
    ResponseWrapper<ProjectsEntity> updateProject(Integer id, ProjectsDto dto);
    ResponseWrapper<String> deleteProject(Integer id);
    ResponseWrapper<ProjectsEntity> getProjectById(Integer id);
    ResponseWrapper<List<ProjectsEntity>> getAllProjects();
}
