package com.reservation_system.host.infrastructure;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;
import java.util.UUID;

@Data
public class ReservationRequest {

    @NotEmpty
    private Integer tableId;

    @NotEmpty
    private Timestamp beginDate;

    @NotEmpty
    private Timestamp endDate;
}
