package com.reservation_system.host.infrastructure;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class ReservationRequest {

    @NotEmpty
    private Integer tableId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotEmpty
    private Date beginDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotEmpty
    private Date endDate;
}
