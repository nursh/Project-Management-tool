package com.nursh.projectmanagementtool.controllers;

import com.nursh.projectmanagementtool.domain.Task;
import com.nursh.projectmanagementtool.services.MapValidationService;
import com.nursh.projectmanagementtool.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/backlogs")
@CrossOrigin(origins = "http://localhost:3000")
public class BacklogController {

    private final TaskService taskService;
    private MapValidationService validationService;

    @Autowired
    public BacklogController(TaskService taskService, MapValidationService validationService) {
        this.taskService = taskService;
        this.validationService = validationService;
    }

    @PostMapping("/{backlogId}")
    public ResponseEntity<?> addTaskToBacklog(
        @Valid @RequestBody Task task,
        BindingResult result,
        @PathVariable String backlogId) {

        ResponseEntity<?> errors = validationService.validate(result);
        if (errors != null) return errors;

        Task addedTask = taskService.addTask(backlogId, task);
        return new ResponseEntity<Task>(addedTask, HttpStatus.CREATED);
    }

    @GetMapping("/{backlogId}")
    public Iterable<Task> getProjectBacklog(@PathVariable String backlogId) {
        return taskService.findBacklogById(backlogId);
    }
}
