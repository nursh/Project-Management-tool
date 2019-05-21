package com.nursh.projectmanagementtool.controllers;

import com.nursh.projectmanagementtool.domain.Project;
import com.nursh.projectmanagementtool.services.MapValidationService;
import com.nursh.projectmanagementtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private ProjectService projectService;
    private MapValidationService validationService;

    @Autowired
    public ProjectController(ProjectService projectService, MapValidationService validationService) {
        this.projectService = projectService;
        this.validationService = validationService;
    }

    @PostMapping("")
    public ResponseEntity<?> createNewProject(
        @Valid @RequestBody Project project,
        BindingResult result) {

        ResponseEntity<?> errors = validationService.validate(result);
        if (errors != null) return errors;

        Project savedProject = projectService.saveOrUpdate(project);
        return new ResponseEntity<Project>(savedProject, HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable String projectId) {
        Project project = projectService.findByIdentifier(projectId);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @GetMapping("")
    public Iterable<Project> getAllProjects() {
        return projectService.findAllProjects();
    }


}
