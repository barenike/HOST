package com.reservation_system.host.controller;

import com.reservation_system.host.model.entity.UserEntity;
import com.reservation_system.host.model.repository.UserRepository;
import com.reservation_system.host.model.service.implementations.UserServiceImplementation;
import com.reservation_system.host.model.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userService = new UserServiceImplementation(userRepository);
    }

    @PostMapping(value = "/registration")
    public ResponseEntity<?> createUser(@RequestBody UserEntity user) {
        try {
            userService.create(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/auth")
    public ResponseEntity<?> authenticate(@RequestBody UserEntity user) {
        try {
        return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
