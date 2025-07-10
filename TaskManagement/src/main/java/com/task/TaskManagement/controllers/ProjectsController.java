package com.task.TaskManagement.controllers;

import com.task.TaskManagement.Entity.ProjectsEntity;
import com.task.TaskManagement.dto.ProjectsDto;
import com.task.TaskManagement.dto.ResponseWrapper;
import com.task.TaskManagement.services.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectsController {

    @Autowired
    private ProjectsService projectService;

    @PostMapping
    public ResponseEntity<ResponseWrapper<ProjectsEntity>> createProject(@RequestBody ProjectsDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(projectService.createProject(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseWrapper<ProjectsEntity>> updateProject(@PathVariable Integer id, @RequestBody ProjectsDto dto) {
        return ResponseEntity.ok(projectService.updateProject(id, dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<String>> deleteProject(@PathVariable Integer id) {
        return ResponseEntity.ok(projectService.deleteProject(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseWrapper<ProjectsEntity>> getProjectById(@PathVariable Integer id) {
        return ResponseEntity.ok(projectService.getProjectById(id));
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper<List<ProjectsEntity>>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }


}

