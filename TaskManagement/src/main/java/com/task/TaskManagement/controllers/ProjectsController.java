package com.task.TaskManagement.controllers;

import com.task.TaskManagement.Entity.ProjectsEntity;
import com.task.TaskManagement.services.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectsController {

    @Autowired
    private ProjectsService projectService;

    @PostMapping
    public ResponseEntity<ProjectsEntity> createProject(@RequestBody ProjectsEntity project) {
        return ResponseEntity.ok(projectService.createProject(project));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectsEntity> updateProject(@PathVariable Integer id, @RequestBody ProjectsEntity project) {
        return ResponseEntity.ok(projectService.updateProject(id, project));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Integer id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectsEntity> getProjectById(@PathVariable Integer id) {
        return ResponseEntity.ok(projectService.getProjectById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProjectsEntity>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }
}

