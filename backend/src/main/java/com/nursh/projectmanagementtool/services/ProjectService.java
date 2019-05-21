package com.nursh.projectmanagementtool.services;

import com.nursh.projectmanagementtool.domain.Project;
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
        return projectRepository.save(project);
    }
}
