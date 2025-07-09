package com.task.TaskManagement.services.Impl;

import com.task.TaskManagement.Entity.ClientEntity;
import com.task.TaskManagement.Entity.ProjectsEntity;
import com.task.TaskManagement.dao.ClientRepository;
import com.task.TaskManagement.dao.ProjectsRepository;
import com.task.TaskManagement.services.ProjectsService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectsServiceImpl implements ProjectsService {
    @Autowired
    private ProjectsRepository projectRepository;
    @Autowired
    private ClientRepository clientRepository;


    @Override
    public ProjectsEntity createProject(ProjectsEntity project) {
        int clientId = project.getClient().getClientId(); // only ID is coming from request
        ClientEntity fullClient = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + clientId));

        project.setClient(fullClient);
        return projectRepository.save(project);
    }

    @Override
    public ProjectsEntity updateProject(Integer id, ProjectsEntity project) {
        ProjectsEntity projects = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));
        projects.setProjectName(project.getProjectName());
       projects.setClient(project.getClient());
        projects.setPriority(project.getPriority());
        projects.setProgressPercent(project.getProgressPercent());
        projects.setStartDate(project.getStartDate());
        projects.setEndDate(project.getEndDate());
        projects.setRemarks(project.getRemarks());
        projects.setUpdatedAt(project.getUpdatedAt());
        return projectRepository.save(project);
    }


    @Override
    public void deleteProject(Integer id) {
        projectRepository.deleteById(id);
    }

    @Override
    public ProjectsEntity getProjectById(Integer id) {
        return projectRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Project not found"));
    }

    @Override
    public List<ProjectsEntity> getAllProjects() {
        return projectRepository.findAll();
    }
}

