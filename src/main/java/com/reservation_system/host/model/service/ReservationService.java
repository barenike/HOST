package com.reservation_system.host.model.service;

import com.reservation_system.host.model.entity.ReservationEntity;
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

    public Optional<ReservationEntity> getOptionalReservationById(UUID reservationId) {
        return reservationRepository.findById(reservationId);
    }

    public ReservationEntity getReservationById(UUID reservationId) {
        return reservationRepository.getById(reservationId);
    }

    public Optional<ReservationEntity> getMyReservationById(UUID reservationId, String userId) throws AccessDeniedException {
        if (reservationRepository.existsById(reservationId)) {
            Optional<ReservationEntity> reservation = getOptionalReservationById(reservationId);
            if (reservation.isPresent() && reservation.get().getUserId().toString().equals(userId)) {
                return reservation;
            } else {
                throw new AccessDeniedException("Forbidden");
            }
        } else {
            return Optional.empty();
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
            if (isSameDay) {
                targetedReservations.add(reservation);
            }
        }
        return targetedReservations;
    }
}
