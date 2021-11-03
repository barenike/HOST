package com.reservation_system.host.model.service;

import com.reservation_system.host.model.entity.ReservationEntity;
import com.reservation_system.host.model.entity.TableEntity;
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

    public Map<String,String> getTablesWithStatus(Date beginDate, Date endDate){
        Map<String,String> result = new HashMap<String,String>();
        enum Status{
            FREE,
            BOOKED,
            UNAVAILABLE
        }

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
                if (!(beginDate.after(reservation.getBeginDate()) || endDate.before(reservation.getEndDate()))){
                    result.put(table.getTableId().toString(), Status.FREE.toString());
                } else {
                    result.put(table.getTableId().toString(), Status.BOOKED.toString());
                }
            }
        }
        return result;

    }

    public Date strToDate(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        Date resultDate = formatter.parse(date);
        return resultDate;
    }

}
