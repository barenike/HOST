package com.reservation_system.host.controller;

import com.reservation_system.host.infrastructure.TableRequest;
import com.reservation_system.host.model.entity.TableEntity;
import com.reservation_system.host.model.entity.TableStatusEnum;
import com.reservation_system.host.model.service.TableService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
public class TableController {

    private final TableService tableService;

    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @GetMapping(value = "/tables")
    public ResponseEntity<Map<Integer, TableStatusEnum>> getTableMap(@ModelAttribute TableRequest tableRequest) {
        try {
            final List<TableEntity> tables = tableService.readAll();
            if (tables == null || tables.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
            Date beginDate = formatter.parse(String.format("%s %s", tableRequest.getBeginDate(), tableRequest.getBeginTime()));
            Date endDate = formatter.parse(String.format("%s %s", tableRequest.getEndDate(), tableRequest.getEndTime()));

            Map<Integer, TableStatusEnum> tableMap = tableService.getTablesWithStatus(tables, beginDate, endDate);
            return new ResponseEntity<>(tableMap, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
