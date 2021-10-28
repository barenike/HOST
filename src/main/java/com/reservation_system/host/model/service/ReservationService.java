package com.reservation_system.host.model.service;

import com.reservation_system.host.model.entity.ReservationEntity;
import com.reservation_system.host.model.repository.ReservationRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
}
