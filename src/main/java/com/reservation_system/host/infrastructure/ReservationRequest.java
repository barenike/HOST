package com.reservation_system.host.infrastructure;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class ReservationRequest implements Serializable {

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
