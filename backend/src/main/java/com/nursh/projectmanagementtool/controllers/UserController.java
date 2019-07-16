package com.nursh.projectmanagementtool.controllers;

import com.nursh.projectmanagementtool.domain.User;
import com.nursh.projectmanagementtool.services.MapValidationService;
import com.nursh.projectmanagementtool.services.UserService;
import com.nursh.projectmanagementtool.validator.UserValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private MapValidationService mapValidationService;
    private UserService userService;
    private UserValidator userValidator;

    public UserController(MapValidationService mapValidationService, UserService userService, UserValidator userValidator) {
        this.mapValidationService = mapValidationService;
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result) {
        userValidator.validate(user, result);
        ResponseEntity<?> errors = mapValidationService.validate(result);
        if (errors != null) return errors;

        User savedUser = userService.saveUser(user);
        return new ResponseEntity<User>(savedUser, HttpStatus.OK);
    }
}
