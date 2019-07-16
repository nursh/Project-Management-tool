package com.nursh.projectmanagementtool.services;

import com.nursh.projectmanagementtool.domain.User;
import com.nursh.projectmanagementtool.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User saveUser(User user) {

            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            return userRepository.save(user);

    }
}
