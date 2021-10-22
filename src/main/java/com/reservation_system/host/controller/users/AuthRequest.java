package com.reservation_system.host.controller.users;

import lombok.Data;

@Data
public class AuthRequest {

    private String login;
    private String password;
}
