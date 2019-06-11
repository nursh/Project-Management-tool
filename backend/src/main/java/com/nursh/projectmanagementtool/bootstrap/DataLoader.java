package com.nursh.projectmanagementtool.bootstrap;

import com.nursh.projectmanagementtool.domain.Project;
import com.nursh.projectmanagementtool.services.ProjectService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.StreamSupport;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProjectService projectService;

    public DataLoader(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Override
    public void run(String... args) throws Exception {
        long count = StreamSupport.stream(projectService.findAllProjects().spliterator(), false).count();
        if (count == 0) {
            loadDate();
        }
    }

    public void loadDate() {
        Project angular = Project
            .builder()
            .name("AngularJS")
            .description("Learning AngularJS8")
            .identifier("ANGJS")
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

        projectService.saveOrUpdate(angular);
        projectService.saveOrUpdate(d3);
        projectService.saveOrUpdate(pixi);
    }
}
