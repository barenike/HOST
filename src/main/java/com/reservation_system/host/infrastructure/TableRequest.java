package com.reservation_system.host.infrastructure;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class TableRequest {

    @NotEmpty
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date beginDate;

    @NotEmpty
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date endDate;
}
