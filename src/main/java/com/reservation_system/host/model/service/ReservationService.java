package com.reservation_system.host.model.service;

import com.reservation_system.host.model.entity.ReservationEntity;
import com.reservation_system.host.model.entity.TableEntity;
import com.reservation_system.host.model.entity.TableStatusEnum;
import com.reservation_system.host.model.repository.ReservationRepository;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public void createReservation(ReservationEntity reservation) {
        reservationRepository.save(reservation);
    }

    public List<ReservationEntity> getAllReservations() {
        return reservationRepository.findAll();
    }

    public ReservationEntity getReservationById(UUID reservationId) {
        return reservationRepository.getById(reservationId);
    }

    public ReservationEntity getMyReservationById(UUID reservationId, String userId) throws AccessDeniedException {
        ReservationEntity reservation = getReservationById(reservationId);
        if (reservation == null) {
            return null;
        } else if (reservation.getUserId().toString().equals(userId)) {
            return reservation;
        } else {
            throw new AccessDeniedException("Forbidden");
        }
    }

    public List<ReservationEntity> getMyReservations(String userId) {
        List<ReservationEntity> allReservations = getAllReservations();
        List<ReservationEntity> targetedReservations = new ArrayList<>();
        if (allReservations == null) {
            return null;
        }
        for (ReservationEntity reservation : allReservations) {
            if (reservation.getUserId().toString().equals(userId)) {
                targetedReservations.add(reservation);
            }
        }
        return targetedReservations;
    }

    public boolean update(ReservationEntity reservation, UUID reservationId) {
        if (reservationRepository.existsById(reservationId)) {
            reservation.setReservationId(reservationId);
            createReservation(reservation);
            return true;
        }
        return false;
    }

    public boolean delete(UUID reservationId) {
        if (reservationRepository.existsById(reservationId)) {
            reservationRepository.deleteById(reservationId);
            return true;
        }
        return false;
    }

    public boolean deleteMyReservation(UUID reservationId, String userId) throws AccessDeniedException {
        if (!reservationRepository.existsById(reservationId)) {
            return false;
        } else if (getReservationById(reservationId).getUserId().toString().equals(userId)) {
            reservationRepository.deleteById(reservationId);
            return true;
        } else {
            throw new AccessDeniedException("Forbidden");
        }
    }

    public List<ReservationEntity> getReservationsByTableId(Integer tableId) {
        List<ReservationEntity> allReservations = getAllReservations();
        List<ReservationEntity> targetedReservations = new ArrayList<>();
        for (ReservationEntity reservation : allReservations) {
            if (Objects.equals(reservation.getTableId(), tableId)) {
                targetedReservations.add(reservation);
            }
        }
        return targetedReservations;
    }

    public List<ReservationEntity> getReservationsOnTableByDate(Integer tableId, Date date) {
        List<ReservationEntity> allReservations = getReservationsByTableId(tableId);
        if (allReservations.isEmpty()) {
            return allReservations;
        }
        List<ReservationEntity> targetedReservations = new ArrayList<>();
        for (ReservationEntity reservation : allReservations) {
            boolean isSameDay = DateUtils.isSameDay(reservation.getBeginDate(), date);
            if (isSameDay && Objects.equals(reservation.getTableId(), tableId)) {
                targetedReservations.add(reservation);
            }
        }
        return targetedReservations;
    }

    public Map<TableEntity, TableStatusEnum> getTablesWithStatus(List<TableEntity> tables, Date beginDate, Date endDate) {
        Map<TableEntity, TableStatusEnum> result = new HashMap<>();
        for (TableEntity table : tables) {
            if (!table.isAvailable()) {
                result.put(table, TableStatusEnum.UNAVAILABLE);
            }
            List<ReservationEntity> reservations = getReservationsOnTableByDate(table.getTableId(), beginDate);
            if (reservations.isEmpty()) {
                return result;
            }
            for (ReservationEntity reservation : reservations) {
                if (beginDate.getTime() >= reservation.getBeginDate().getTime() && beginDate.getTime() <= reservation.getEndDate().getTime()
                || endDate.getTime() >= reservation.getBeginDate().getTime() && endDate.getTime() <= reservation.getEndDate().getTime()) {
                    for (var entry : result.entrySet()) {
                        if (Objects.equals(entry.getKey().getTableId(), reservation.getTableId())) {
                            entry.setValue(TableStatusEnum.RESERVED);
                        }
                    }
                } else {
                    for (var entry : result.entrySet()) {
                        if (Objects.equals(entry.getKey().getTableId(), reservation.getTableId())) {
                            entry.setValue(TableStatusEnum.AVAILABLE);
                        }
                    }
                }
            }
        }
        return result;
    }
}
