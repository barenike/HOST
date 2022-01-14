package com.reservation_system.host.infrastructure;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthRequest implements Serializable {

    private String email;

    private String password;
}
