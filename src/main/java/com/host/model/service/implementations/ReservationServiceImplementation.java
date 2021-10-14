package com.host.model.service.implementations;

import com.host.model.entity.ReservationEntity;
import com.host.model.repository.ReservationRepository;
import com.host.model.service.interfaces.ReservationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReservationServiceImplementation implements ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationServiceImplementation(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void create(ReservationEntity reservation) {
        reservationRepository.save(reservation);
    }

    @Override
    public List<ReservationEntity> readAll() {
        return reservationRepository.findAll();
    }

    @Override
    public ReservationEntity read(UUID id) {
        return reservationRepository.getById(id);
    }

    @Override
    public boolean update(ReservationEntity reservation, UUID id) {
        if (reservationRepository.existsById(id)) {
            reservation.setReservationId(id);
            reservationRepository.save(reservation);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(UUID id) {
        if (reservationRepository.existsById(id)) {
            reservationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
