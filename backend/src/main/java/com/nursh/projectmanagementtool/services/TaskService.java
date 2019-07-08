package com.nursh.projectmanagementtool.services;

import com.nursh.projectmanagementtool.domain.Backlog;
import com.nursh.projectmanagementtool.domain.Task;
import com.nursh.projectmanagementtool.exceptions.ProjectNotFoundException;
import com.nursh.projectmanagementtool.repositories.BacklogRepository;
import com.nursh.projectmanagementtool.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final BacklogRepository backlogRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, BacklogRepository backlogRepository) {
        this.taskRepository = taskRepository;
        this.backlogRepository = backlogRepository;
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

            return taskRepository.save(task);
        } catch (Exception e) {
            throw new ProjectNotFoundException("Project not found");
        }
    }


    public Iterable<Task> findBacklogById(String identifier) {
        return taskRepository.findByProjectIdenfierOrderByPriority(identifier);
    }
}
