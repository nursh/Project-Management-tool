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
@RequestMapping("/api/backlog")
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

    @GetMapping("/{backlogId}/tasks/{taskId}")
    public ResponseEntity<?> getTask(@PathVariable String backlogId, @PathVariable String taskId) {
        Task task = taskService.findTaskByProjectSequence(backlogId, taskId);
        return new ResponseEntity<Task>(task, HttpStatus.OK);
    }

    @PatchMapping("/{backlogId}/tasks/{taskId}")
    public ResponseEntity<?> updateByProjectSequence(
        @Valid @RequestBody Task task,
        BindingResult result,
        @PathVariable String backlogId,
        @PathVariable String taskId) {

        ResponseEntity<?> errors = validationService.validate(result);
        if (errors != null) return errors;

        Task updatedTask = taskService.updateTaskByProjectSequence(task, backlogId, taskId);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @DeleteMapping("/{backlogId}/tasks/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable String backlogId, @PathVariable String taskId) {
        taskService.deleteTaskByProjectSequence(backlogId, taskId);
        return new ResponseEntity<>("Project Task: '" + taskId + "' was successfully deleted.", HttpStatus.OK);
    }
}
