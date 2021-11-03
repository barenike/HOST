package com.reservation_system.host.controller;

import com.reservation_system.host.configuration.jwt.JwtProvider;
import com.reservation_system.host.infrastructure.AuthRequest;
import com.reservation_system.host.infrastructure.AuthResponse;
import com.reservation_system.host.infrastructure.RegistrationRequest;
import com.reservation_system.host.model.entity.UserEntity;
import com.reservation_system.host.model.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {
    private final UserService userService;
    private final JwtProvider jwtProvider;

    public UserController(UserService userService, JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
        try {
            UserEntity user = new UserEntity();
            user.setEmail(registrationRequest.getEmail());
            user.setPassword(registrationRequest.getPassword());
            userService.create(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthResponse> auth(@RequestBody AuthRequest request) {
        try {
            UserEntity userEntity = userService.findByEmailAndPassword(request.getEmail(), request.getPassword());
            if (userEntity != null) {
                String token = jwtProvider.generateToken(String.valueOf(userEntity.getUserId()));
                return new ResponseEntity<>(new AuthResponse(token), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new AuthResponse(null), HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}