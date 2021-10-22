package com.reservation_system.host.model.service;

import com.reservation_system.host.model.entity.TableEntity;
import com.reservation_system.host.model.repository.TableRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TableService {

    private final TableRepository tableRepository;

    public TableService(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    public void create(TableEntity table) {
        tableRepository.save(table);
    }

    public List<TableEntity> readAll() {
        return tableRepository.findAll();
    }

    public TableEntity read(UUID id) {
        return tableRepository.getById(id);
    }

    public boolean update(TableEntity table, UUID id) {
        if (tableRepository.existsById(id)) {
            table.setTableId(id);
            create(table);
            return true;
        }
        return false;
    }

    public boolean delete(UUID id) {
        if (tableRepository.existsById(id)) {
            tableRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
