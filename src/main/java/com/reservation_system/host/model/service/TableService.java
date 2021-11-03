package com.reservation_system.host.model.service;

import com.reservation_system.host.model.entity.ReservationEntity;
import com.reservation_system.host.model.entity.TableEntity;
import com.reservation_system.host.model.entity.TableStatusEnum;
import com.reservation_system.host.model.repository.TableRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TableService {

    private final TableRepository tableRepository;
    private final ReservationService reservationService;

    public TableService(TableRepository tableRepository, ReservationService reservationService) {
        this.tableRepository = tableRepository;
        this.reservationService = reservationService;
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

    public Map<Integer, TableStatusEnum> getTablesWithStatus(List<TableEntity> tables, Date beginDate, Date endDate) {
        Map<Integer, TableStatusEnum> result = new HashMap<>();
        for (TableEntity table : tables) {
            if (!table.isAvailable()) {
                result.put(table.getTableId(), TableStatusEnum.UNAVAILABLE);
            } else {
                List<ReservationEntity> reservations = reservationService.getReservationsOnTableByDate(table.getTableId(), beginDate);
                if (reservations.isEmpty()) {
                    return result;
                }
                for (ReservationEntity reservation : reservations) {
                    if ((beginDate.getTime() >= reservation.getBeginDate().getTime() && beginDate.getTime() <= reservation.getEndDate().getTime())
                            || (endDate.getTime() >= reservation.getBeginDate().getTime() && endDate.getTime() <= reservation.getEndDate().getTime())
                            || (beginDate.getTime() < reservation.getBeginDate().getTime() && endDate.getTime() > reservation.getEndDate().getTime())) {
                        result.put(table.getTableId(), TableStatusEnum.RESERVED);
                    } else {
                        result.put(table.getTableId(), TableStatusEnum.AVAILABLE);
                    }
                }
            }
        }
        return result;
    }
}
