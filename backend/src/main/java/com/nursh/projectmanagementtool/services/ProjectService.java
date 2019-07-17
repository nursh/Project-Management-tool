package com.nursh.projectmanagementtool.services;

import com.nursh.projectmanagementtool.domain.Backlog;
import com.nursh.projectmanagementtool.domain.Project;
import com.nursh.projectmanagementtool.domain.User;
import com.nursh.projectmanagementtool.exceptions.ProjectIDException;
import com.nursh.projectmanagementtool.exceptions.ProjectNotFoundException;
import com.nursh.projectmanagementtool.repositories.BacklogRepository;
import com.nursh.projectmanagementtool.repositories.ProjectRepository;
import com.nursh.projectmanagementtool.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private ProjectRepository projectRepository;
    private BacklogRepository backlogRepository;
    private UserRepository userRepository;


    @Autowired
    public ProjectService(ProjectRepository projectRepository, BacklogRepository backlogRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.backlogRepository = backlogRepository;
        this.userRepository = userRepository;
    }

    public Project saveOrUpdate(Project project, String username) {

        if (project.getId() != null) {
            Project existingProject = projectRepository.findByIdentifier(project.getIdentifier());

            if (existingProject != null &&
                (!existingProject.getLeader().equals(username))
            ) {
                throw new ProjectNotFoundException("Project not found in your account");
            } else if (existingProject == null) {
                throw new ProjectNotFoundException("Project with ID: '"
                    + project.getIdentifier() + "' cannot be updated. The project does not exist");
            }
        }

        try {

            User user = userRepository.findByUsername(username);
            project.setUser(user);
            project.setLeader(user.getFullName());

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

    public Project findByIdentifier(String identifier, String username) {
        Project project = projectRepository.findByIdentifier(identifier.toUpperCase());

        if (project == null) {
            throw new ProjectIDException("Project does not exist");
        }

        if (!project.getLeader().equals(username)) {
            throw new ProjectNotFoundException("Project not found in your account");
        }

        return project;
    }

    public Iterable<Project> findAllProjects(String username) {
        return projectRepository.findAllByLeader(username);
    }

    public void deleteProject(String identifier, String username) {
        Project project = findByIdentifier(identifier, username);
        if (project != null)  projectRepository.delete(project);
    }

    public void saveAll(List<Project> projects, String username) {
        for (Project project : projects) {
            saveOrUpdate(project, username);
        }
    }
}
