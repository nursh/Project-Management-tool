package com.nursh.projectmanagementtool.repositories;

import com.nursh.projectmanagementtool.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
