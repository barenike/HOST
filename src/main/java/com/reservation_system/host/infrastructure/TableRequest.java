package com.reservation_system.host.infrastructure;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class TableRequest {

    @NotEmpty
    private String beginDate;
//2021-12-12
    @NotEmpty
    private String beginTime;
    //16:30

    @NotEmpty
    private String endDate;

    @NotEmpty
    private String endTime;
}
