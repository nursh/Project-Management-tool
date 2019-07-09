package com.nursh.projectmanagementtool.services;

import com.nursh.projectmanagementtool.domain.Backlog;
import com.nursh.projectmanagementtool.domain.Project;
import com.nursh.projectmanagementtool.exceptions.ProjectIDException;
import com.nursh.projectmanagementtool.repositories.BacklogRepository;
import com.nursh.projectmanagementtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private ProjectRepository projectRepository;
    private BacklogRepository backlogRepository;


    @Autowired
    public ProjectService(ProjectRepository projectRepository, BacklogRepository backlogRepository) {
        this.projectRepository = projectRepository;
        this.backlogRepository = backlogRepository;
    }

    public Project saveOrUpdate(Project project) {

        try {
            project.setIdentifier(project.getIdentifier().toUpperCase());

            if (project.getId() == null) {
                Backlog backlog = new Backlog();
                project.setBacklog(backlog);
                backlog.setProject(project);
                backlog.setProjectIdentifier(project.getIdentifier());
            } else {
                project.setBacklog(backlogRepository.findByProjectIdentifier(project.getIdentifier()));
            }

            return projectRepository.save(project);
        } catch(Exception e) {
            throw new ProjectIDException("Project Identifier: "
                + project.getIdentifier().toUpperCase() + " already exists.");
        }

    }

    public Project findByIdentifier(String identifier) {
        Project project = projectRepository.findByIdentifier(identifier.toUpperCase());

        if (project == null) {
            throw new ProjectIDException("Project does not exist");
        }

        return project;
    }

    public Iterable<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    public void deleteProject(String identifier) {
        Project project = projectRepository.findByIdentifier(identifier.toUpperCase());

        if (project == null) {
            throw new ProjectIDException("Project does not exist");
        }

        projectRepository.delete(project);
    }

    public void saveAll(List<Project> projects) {
        for (Project project : projects) {
            saveOrUpdate(project);
        }
    }
}
