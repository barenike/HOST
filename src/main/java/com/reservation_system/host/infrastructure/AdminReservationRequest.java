package com.reservation_system.host.infrastructure;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.UUID;

@Data
public class AdminReservationRequest implements Serializable {

    @NotEmpty
    private UUID userId;

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
