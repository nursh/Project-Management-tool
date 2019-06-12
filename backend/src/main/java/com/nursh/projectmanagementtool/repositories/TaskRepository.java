package com.nursh.projectmanagementtool.repositories;

import com.nursh.projectmanagementtool.domain.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
}
