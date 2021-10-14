package com.host.controller;

import com.host.model.entity.TableEntity;
import com.host.model.repository.TableRepository;
import com.host.model.service.implementations.TableServiceImplementation;
import com.host.model.service.interfaces.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tables")
public class TableController {
    private final TableRepository tableRepository;
    private final TableService tableService;

    @Autowired
    public TableController(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
        this.tableService = new TableServiceImplementation(this.tableRepository);
    }

    @GetMapping(value = "/tables")
    public ResponseEntity<?> getFreeTables(@RequestBody TableEntity table) {
        try {
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
