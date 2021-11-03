package com.reservation_system.host.infrastructure;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class ReservationDates {

    @NotEmpty
    private Date beginDate;

    @NotEmpty
    private Date endDate;
}
