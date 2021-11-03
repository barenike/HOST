package com.reservation_system.host.infrastructure;

import lombok.Data;

@Data
public class AuthRequest {

    private String email;

    private String password;
}
