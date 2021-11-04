package com.reservation_system.host.infrastructure;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class TableRequest {

    @NotEmpty
    private String beginDate;

    @NotEmpty
    private String endDate;
}
