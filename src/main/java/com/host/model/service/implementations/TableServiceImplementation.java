package com.host.model.service.implementations;

import com.host.model.entity.TableEntity;
import com.host.model.repository.TableRepository;
import com.host.model.service.interfaces.TableService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TableServiceImplementation implements TableService {

    private final TableRepository tableRepository;

    public TableServiceImplementation(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    @Override
    public void create(TableEntity table) {
        tableRepository.save(table);
    }

    @Override
    public List<TableEntity> readAll() {
        return tableRepository.findAll();
    }

    @Override
    public TableEntity read(UUID id) {
        return tableRepository.getById(id);
    }

    @Override
    public boolean update(TableEntity table, UUID id) {
        if (tableRepository.existsById(id)) {
            table.setTableId(id);
            tableRepository.save(table);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(UUID id) {
        if (tableRepository.existsById(id)) {
            tableRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
