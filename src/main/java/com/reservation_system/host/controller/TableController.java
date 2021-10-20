package com.reservation_system.host.controller;

import com.reservation_system.host.model.repository.TableRepository;
import com.reservation_system.host.model.service.implementations.TableServiceImplementation;
import com.reservation_system.host.model.service.interfaces.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tables")
public class TableController {

    private final TableService tableService;

    @Autowired
    public TableController(TableRepository tableRepository) {
        this.tableService = new TableServiceImplementation(tableRepository);
    }

    @GetMapping(value = "/tables")
    public ResponseEntity<?> getFreeTables() {
        try {
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
