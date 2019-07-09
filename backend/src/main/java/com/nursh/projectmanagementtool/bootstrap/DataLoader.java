package com.nursh.projectmanagementtool.bootstrap;

import com.nursh.projectmanagementtool.domain.Project;
import com.nursh.projectmanagementtool.domain.Task;
import com.nursh.projectmanagementtool.services.ProjectService;
import com.nursh.projectmanagementtool.services.TaskService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {


    private final ProjectService projectService;
    private final TaskService taskService;


    public DataLoader(ProjectService projectService, TaskService taskService) {
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    public void loadData() {
        Project angular = Project
            .builder()
            .name("AngularJS")
            .description("Learning AngularJS8")
            .identifier("ANGJS")
            .build();

        Task task1 = Task
            .builder()
            .summary("First Task")
            .build();

        Task task2 = Task
            .builder()
            .summary("Second Task")
            .build();

        Task task3 = Task
            .builder()
            .summary("Third Task")
            .build();

        Project d3 = Project
            .builder()
            .name("D3 - Data visualization")
            .description("Data Visualization with d3 Library")
            .identifier("D3js")
            .build();

        Project pixi = Project
            .builder()
            .name("Pixi.js - 2d animations")
            .description("2d animations for the web")
            .identifier("Pixi")
            .build();

        projectService.saveAll(Arrays.asList(angular, d3, pixi));
        taskService.saveAll(angular.getIdentifier(), Arrays.asList(task1, task2, task3));
    }
}
