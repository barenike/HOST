package com.reservation_system.host.model.service;

import com.reservation_system.host.model.entity.ReservationEntity;
import com.reservation_system.host.model.entity.TableEntity;
import com.reservation_system.host.model.entity.TableStatusEnum;
import com.reservation_system.host.model.repository.TableRepository;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TableService {

    ReservationService reservationService;

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

    public Map<Integer,TableStatusEnum> getTablesWithStatus(Date beginDate, Date endDate){
        Map<Integer,TableStatusEnum> result = new HashMap<Integer,TableStatusEnum>();

        for(TableEntity table : readAll()){
            if (!table.isAvailable()){
                result.put(table.getTableId().toString(), Status.UNAVAILABLE.toString());
                continue;
            }
            ArrayList<ReservationEntity> reservations = (ArrayList<ReservationEntity>)
                    reservationService.getReservationsOnTableByDate(table.getTableId().toString(), beginDate);

            if (reservations == null || reservations.isEmpty()){
                result.put(table.getTableId().toString(), Status.FREE.toString());
                continue;
            }

            for(ReservationEntity reservation : reservations) {
                if ((beginDate.getTime() >= reservation.getBeginDate().getTime() && beginDate.getTime() <= reservation.getEndDate().getTime())
                        || (endDate.getTime() >= reservation.getBeginDate().getTime() && endDate.getTime() <= reservation.getEndDate().getTime())
                        || (beginDate.getTime() < reservation.getBeginDate().getTime() && endDate.getTime() > reservation.getEndDate().getTime())) {
                    result.put(table.getTableId(), TableStatusEnum.RESERVED);
                } else {
                    result.put(table.getTableId(), TableStatusEnum.AVAILABLE);
                }
            }
        }
        return result;

    }

    public Date strToDate(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        return formatter.parse(date);
    }

}
