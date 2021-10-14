package com.host.controller;

import com.host.entity.UserEntity;
import com.host.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity registration(@RequestBody UserEntity user) {
        try {
            userRepository.save(user);
            return ResponseEntity.ok("Registration is successful!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Exception occurred!");
        }
    }

    @GetMapping("/")
    public ResponseEntity getUsers() {
        try {
            return ResponseEntity.ok("The server is running!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Exception occurred!");
        }
    }
}
