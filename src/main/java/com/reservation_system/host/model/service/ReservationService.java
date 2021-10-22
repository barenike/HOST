package com.reservation_system.host.model.service;

import com.reservation_system.host.model.entity.ReservationEntity;
import com.reservation_system.host.model.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public void create(ReservationEntity reservation) {
        reservationRepository.save(reservation);
    }

    public List<ReservationEntity> readAll() {
        return reservationRepository.findAll();
    }

    public ReservationEntity read(UUID id) {
        return reservationRepository.getById(id);
    }

    public boolean update(ReservationEntity reservation, UUID id) {
        if (reservationRepository.existsById(id)) {
            reservation.setReservationId(id);
            create(reservation);
            return true;
        }
        return false;
    }

    public boolean delete(UUID id) {
        if (reservationRepository.existsById(id)) {
            reservationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
