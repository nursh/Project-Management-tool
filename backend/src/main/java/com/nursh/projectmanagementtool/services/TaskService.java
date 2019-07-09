package com.nursh.projectmanagementtool.services;

import com.nursh.projectmanagementtool.domain.Backlog;
import com.nursh.projectmanagementtool.domain.Project;
import com.nursh.projectmanagementtool.domain.Task;
import com.nursh.projectmanagementtool.exceptions.ProjectNotFoundException;
import com.nursh.projectmanagementtool.repositories.BacklogRepository;
import com.nursh.projectmanagementtool.repositories.ProjectRepository;
import com.nursh.projectmanagementtool.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final BacklogRepository backlogRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, BacklogRepository backlogRepository, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.backlogRepository = backlogRepository;
        this.projectRepository = projectRepository;
    }

    public Task addTask(String projectIdentifier, Task task) {

        try {
            Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
            task.setBacklog(backlog);
            int backlogSequence = backlog.getPTSequence();
            backlogSequence += 1;
            backlog.setPTSequence(backlogSequence);
            task.setProjectSequence(projectIdentifier + "-" + backlogSequence);
            task.setProjectIdenfier(projectIdentifier);

            if (task.getPriority() == null) {
                task.setPriority(3);
            }

            if (task.getStatus() == "" || task.getStatus() == null) {
                task.setStatus("TO_DO");
            }
            backlogRepository.save(backlog);
            return taskRepository.save(task);
        } catch (Exception e) {
            throw new ProjectNotFoundException("Project not found");
        }
    }

    public void saveAll(String identifier, List<Task> tasks) {
        for (Task task: tasks) {
            addTask(identifier, task);
        }
    }


    public Iterable<Task> findBacklogById(String identifier) {
        Project project = projectRepository.findByIdentifier(identifier);
        if (project == null) {
            throw new ProjectNotFoundException("Project with ID: '" + identifier + "' does not exist");
        }
        return taskRepository.findByProjectIdenfierOrderByPriority(identifier);
    }

    public Task findTaskByProjectSequence(String backlogId, String sequence) {
        Backlog backlog = backlogRepository.findByProjectIdentifier(backlogId);
        if (backlog == null) {
            throw new ProjectNotFoundException("Project with ID: '" + backlogId + "' does not exist");
        }

        Task task = taskRepository.findByProjectSequence(sequence);
        if (task == null) {
            throw new ProjectNotFoundException("Project task: '" + sequence + "' not found");
        }

        if (!task.getProjectIdenfier().equals(backlogId)) {
            throw new ProjectNotFoundException("Project task: '" + sequence +
                "' does not exist in project: " + backlogId);
        }
        return task;
    }

    public Task updateTaskByProjectSequence(Task task,  String backlogId, String sequence) {
        Backlog backlog = backlogRepository.findByProjectIdentifier(backlogId);
        if (backlog == null) {
            throw new ProjectNotFoundException("Project with ID: '" + backlogId + "' does not exist");
        }

        Task oldTask = taskRepository.findByProjectSequence(sequence);
        if (oldTask == null) {
            throw new ProjectNotFoundException("Project task: '" + sequence + "' not found");
        }

        if (!oldTask.getProjectIdenfier().equals(backlogId)) {
            throw new ProjectNotFoundException("Project task: '" + sequence +
                "' does not exist in project: " + backlogId);
        }

        Task updatedTask = taskRepository.save(task);
        return updatedTask;
    }
}
