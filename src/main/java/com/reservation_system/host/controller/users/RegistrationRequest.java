package com.reservation_system.host.controller.users;

import lombok.Data;
import javax.validation.constraints.NotEmpty;

@Data
public class RegistrationRequest {

    @NotEmpty
    private String login;

    @NotEmpty
    private String password;
}
