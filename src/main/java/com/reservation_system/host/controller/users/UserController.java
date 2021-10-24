package com.reservation_system.host.controller.users;

import com.reservation_system.host.configuration.jwt.JwtProvider;
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
            String passwordPat = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,20}";
            if (!registrationRequest.getPassword().matches(passwordPat)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            if (registrationRequest.getLogin().replaceAll(" ", "").length() < 6) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            UserEntity user = new UserEntity();
            user.setPassword(registrationRequest.getPassword());
            user.setLogin(registrationRequest.getLogin());
            userService.create(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody AuthRequest request) {
        UserEntity userEntity = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        String token = jwtProvider.generateToken(userEntity.getLogin());
        return new AuthResponse(token);
    }
}
