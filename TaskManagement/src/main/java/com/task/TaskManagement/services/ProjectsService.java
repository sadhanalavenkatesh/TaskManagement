package com.task.TaskManagement.services;

import com.task.TaskManagement.Entity.ProjectsEntity;

import java.util.List;

public interface ProjectsService {
    ProjectsEntity createProject(ProjectsEntity project);
    ProjectsEntity updateProject(Integer id, ProjectsEntity project);
    void deleteProject(Integer id);
    ProjectsEntity getProjectById(Integer id);
    List<ProjectsEntity> getAllProjects();
}
