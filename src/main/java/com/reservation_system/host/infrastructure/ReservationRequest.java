package com.reservation_system.host.infrastructure;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ReservationRequest {

    @NotEmpty
    private Integer tableId;

    @NotEmpty
    private String beginDate;

    @NotEmpty
    private String beginTime;

    @NotEmpty
    private String endDate;

    @NotEmpty
    private String endTime;
}
