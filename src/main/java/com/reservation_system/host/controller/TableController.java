package com.reservation_system.host.controller;

import com.reservation_system.host.model.entity.TableEntity;
import com.reservation_system.host.model.service.TableService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TableController {

    private final TableService tableService;

    public TableController(TableService tableService) {
        this.tableService = tableService;
    }
    //Доделать
    @GetMapping(value = "/user/tables")
    public ResponseEntity<List<TableEntity>> getFreeTables() {
        try {
            final List<TableEntity> tables = tableService.readAll();

            return tables != null && !tables.isEmpty()
                    ? new ResponseEntity<>(tables, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
