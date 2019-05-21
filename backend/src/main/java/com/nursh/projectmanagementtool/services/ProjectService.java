package com.nursh.projectmanagementtool.services;

import com.nursh.projectmanagementtool.domain.Project;
import com.nursh.projectmanagementtool.exceptions.ProjectIDException;
import com.nursh.projectmanagementtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project saveOrUpdate(Project project) {

        try {
            project.setIdentifier(project.getIdentifier().toUpperCase());
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
}
