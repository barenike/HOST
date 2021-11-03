package com.reservation_system.host.controller;

import com.reservation_system.host.model.service.TableService;
import com.reservation_system.host.model.entity.TableStatusEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
public class TableController {

    private final TableService tableService;

    public TableController(TableService tableService) {
        this.tableService = tableService;
    }
    @GetMapping(value = "/user/tables/")
    public ResponseEntity<Map<Integer,TableStatusEnum>> getFreeTables() {
        try {
            String d1 = "20-10-2021 15:30:00";
            String d2 = "20-10-2021 16:30:00";
            Date beginDate = tableService.strToDate(d1);
            Date endDate = tableService.strToDate(d2);
            return new ResponseEntity<>(tableService.getTablesWithStatus(beginDate, endDate), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
