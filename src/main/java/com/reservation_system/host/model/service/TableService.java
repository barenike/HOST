package com.reservation_system.host.model.service;

import com.reservation_system.host.model.entity.ReservationEntity;
import com.reservation_system.host.model.entity.TableEntity;
import com.reservation_system.host.model.repository.TableRepository;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public TableEntity read(Integer tableId) {
        return tableRepository.getById(tableId);
    }

    public boolean update(TableEntity table, Integer tableId) {
        if (tableRepository.existsById(tableId)) {
            table.setTableId(tableId);
            create(table);
            return true;
        }
        return false;
    }

    public boolean delete(Integer tableId) {
        if (tableRepository.existsById(tableId)) {
            tableRepository.deleteById(tableId);
            return true;
        }
        return false;
    }

    public Map<TableEntity,Integer> getTablesWithStatus(List<TableEntity> tables, Date beginDate, Date endDate){
        Map<TableEntity,Integer> result = new HashMap<TableEntity,Integer>();

        for(TableEntity table : tables){
            if (!table.isAvailable()){
                result.put(table, 3);
            }
            ArrayList<ReservationEntity> reservations = ReservationService.getReservationsOnTableByDate(table.getTableId(), beginDate);
            //не работает, посмотреть как использовать тут этот метод и дописать логику проверки
        }
        return result;

    }
}
