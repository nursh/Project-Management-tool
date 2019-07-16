package com.nursh.projectmanagementtool.repositories;

import com.nursh.projectmanagementtool.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
    User getById(Long id);

    // Could be Optional<User> findById(Long id)
}
