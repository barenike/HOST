package com.reservation_system.host.infrastructure;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class RegistrationRequest implements Serializable {

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;
}
