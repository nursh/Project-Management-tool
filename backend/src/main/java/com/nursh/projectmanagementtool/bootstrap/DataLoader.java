package com.nursh.projectmanagementtool.bootstrap;

import com.nursh.projectmanagementtool.domain.Project;
import com.nursh.projectmanagementtool.domain.Task;
import com.nursh.projectmanagementtool.domain.User;
import com.nursh.projectmanagementtool.services.ProjectService;
import com.nursh.projectmanagementtool.services.TaskService;
import com.nursh.projectmanagementtool.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.stream.StreamSupport;

@Component
public class DataLoader implements CommandLineRunner {


    private final ProjectService projectService;
    private final TaskService taskService;
    private final UserService userService;


    public DataLoader(ProjectService projectService, TaskService taskService, UserService userService) {
        this.projectService = projectService;
        this.taskService = taskService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        long count = StreamSupport.stream(projectService.findAllProjects().spliterator(), false).count();
        if (count <= 0) {
            loadData();
        }

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
            .acceptanceCriteria("Finish first task")
            .dueDate(new Date())
            .summary("First Task")
            .build();

        Task task2 = Task
            .builder()
            .acceptanceCriteria("Finish second task")
            .dueDate(new Date())
            .summary("Second Task")
            .build();

        Task task3 = Task
            .builder()
            .acceptanceCriteria("Finish third task")
            .dueDate(new Date())
            .summary("Third Task")
            .build();

        Project d3 = Project
            .builder()
            .name("D3 - Data visualization")
            .description("Data Visualization with d3 Library")
            .identifier("D3js")
            .build();

        Task task4 = Task
            .builder()
            .acceptanceCriteria("Finish Line Graph")
            .dueDate(new Date())
            .summary("Line Graph")
            .build();

        Task task5 = Task
            .builder()
            .acceptanceCriteria("Finish Pie chart")
            .dueDate(new Date())
            .summary("Pie Chart")
            .build();

        Project pixi = Project
            .builder()
            .name("Pixi.js - 2d animations")
            .description("2d animations for the web")
            .identifier("Pixi")
            .build();

        User user1 = User
            .builder()
            .username("Leroy@gmail.com")
            .password("password")
            .fullName("Leroy Jenkins")
            .build();


        userService.saveUser(user1);
        projectService.saveAll(Arrays.asList(angular, d3, pixi), user1.getUsername());
        taskService.saveAll(angular.getIdentifier(), Arrays.asList(task1, task2, task3));
        taskService.saveAll(d3.getIdentifier(), Arrays.asList(task4, task5));

        System.out.println("Loaded all test data...");
    }
}
