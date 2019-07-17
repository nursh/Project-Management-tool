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
import java.security.Principal;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "http://localhost:3000")
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
        BindingResult result,
        Principal principal) {

        ResponseEntity<?> errors = validationService.validate(result);
        if (errors != null) return errors;

        Project savedProject = projectService.saveOrUpdate(project, principal.getName());
        return new ResponseEntity<Project>(savedProject, HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable String projectId, Principal principal) {
        Project project = projectService.findByIdentifier(projectId, principal.getName());
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @GetMapping("")
    public Iterable<Project> getAllProjects(Principal principal) {
        return projectService.findAllProjects(principal.getName());
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectId, Principal principal) {
        projectService.deleteProject(projectId, principal.getName());
        return new ResponseEntity<String>("Project " + projectId + " was deleted", HttpStatus.OK);
    }
}
