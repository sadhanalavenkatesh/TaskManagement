package com.task.TaskManagement.services.Impl;

import com.task.TaskManagement.Entity.ClientEntity;
import com.task.TaskManagement.Entity.ProjectsEntity;
import com.task.TaskManagement.dao.ClientRepository;
import com.task.TaskManagement.dao.ProjectsRepository;
import com.task.TaskManagement.dto.ProjectsDto;
import com.task.TaskManagement.dto.ResponseWrapper;
import com.task.TaskManagement.services.ProjectsService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProjectsServiceImpl implements ProjectsService {
    @Autowired
    private ProjectsRepository projectRepository;
    @Autowired
    private ClientRepository clientRepository;


    @Override
    public ResponseWrapper<ProjectsEntity> createProject(ProjectsDto dto) {
        ClientEntity client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + dto.getClientId()));

        ProjectsEntity project = new ProjectsEntity();
        BeanUtils.copyProperties(dto, project); // Automatically maps matching fields
        project.setClient(client);
        project.setCreatedAt(LocalDateTime.now());
        project.setUpdatedAt(LocalDateTime.now());

        ProjectsEntity saved = projectRepository.save(project);
        return new ResponseWrapper<>("Project created successfully", null);
    }

    @Override
    public ResponseWrapper<ProjectsEntity> updateProject(Integer id, ProjectsDto dto) {
        ProjectsEntity existing = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));

        ClientEntity client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + dto.getClientId()));

        BeanUtils.copyProperties(dto, existing,"projectId"); // Copy all matching fields from DTO
        existing.setClient(client);
        existing.setUpdatedAt(LocalDateTime.now());

        ProjectsEntity updated = projectRepository.save(existing);
        return new ResponseWrapper<>("Project updated successfully", null);
    }

    @Override
    public ResponseWrapper<String> deleteProject(Integer id) {
        if (!projectRepository.existsById(id)) {
        throw new EntityNotFoundException("Project not found");
    }
        projectRepository.deleteById(id);
        return new ResponseWrapper<>("Project deleted successfully", null);
    }

    @Override
    public ResponseWrapper<ProjectsEntity> getProjectById(Integer id) {
        ProjectsEntity project = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));
        return new ResponseWrapper<>("Project fetched successfully", project);
    }

    @Override
    public ResponseWrapper<List<ProjectsEntity>> getAllProjects() {
        List<ProjectsEntity> list = projectRepository.findAll();
        return new ResponseWrapper<>("All projects fetched successfully", list);
    }
}

