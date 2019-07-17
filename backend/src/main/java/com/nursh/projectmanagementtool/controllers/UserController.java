package com.nursh.projectmanagementtool.controllers;

import com.nursh.projectmanagementtool.domain.User;
import com.nursh.projectmanagementtool.payload.JWTLoginSuccessResponse;
import com.nursh.projectmanagementtool.payload.LoginRequest;
import com.nursh.projectmanagementtool.security.JWTTokenProvider;
import com.nursh.projectmanagementtool.services.MapValidationService;
import com.nursh.projectmanagementtool.services.UserService;
import com.nursh.projectmanagementtool.validator.UserValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.nursh.projectmanagementtool.security.SecurityConstants.TOKEN_PREFIX;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private MapValidationService mapValidationService;
    private UserService userService;
    private UserValidator userValidator;
    private JWTTokenProvider tokenProvider;
    private AuthenticationManager authenticationManager;

    public UserController(MapValidationService mapValidationService, UserService userService, UserValidator userValidator, JWTTokenProvider tokenProvider, AuthenticationManager authenticationManager) {
        this.mapValidationService = mapValidationService;
        this.userService = userService;
        this.userValidator = userValidator;
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result) {
        ResponseEntity<?> errors = mapValidationService.validate(result);
        if (errors != null) return errors;

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
            )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = TOKEN_PREFIX + tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTLoginSuccessResponse(true, jwt));

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
