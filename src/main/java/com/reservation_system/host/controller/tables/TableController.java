package com.reservation_system.host.controller.tables;

import com.reservation_system.host.model.repository.TableRepository;
import com.reservation_system.host.model.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TableController {

    private final TableService tableService;

    @Autowired
    public TableController(TableRepository tableRepository) {
        this.tableService = new TableService(tableRepository);
    }

    @GetMapping(value = "user/tables/receive")
    public ResponseEntity<?> getFreeTables() {
        try {
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
