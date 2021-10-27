package com.reservation_system.host.controller;

import com.reservation_system.host.model.entity.ReservationEntity;
import com.reservation_system.host.model.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping(value = "/user/reservations")
    public ResponseEntity<?> createReservation(@RequestBody ReservationEntity reservation) {
        try {
            reservationService.create(reservation);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/user/reservations")
    public ResponseEntity<List<ReservationEntity>> getReservations() {
        try {
            final List<ReservationEntity> reservations = reservationService.readAll();

            return reservations != null && !reservations.isEmpty()
                    ? new ResponseEntity<>(reservations, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/user/reservations/{reservation_id}")
    public ResponseEntity<?> deleteReservation(@PathVariable(name = "reservation_id") UUID reservationId) {
        try {
            final boolean isDeleted = reservationService.delete(reservationId);

            return isDeleted
                    ? new ResponseEntity<>(HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/user/reservations/{reservation_id}")
    public ResponseEntity<ReservationEntity> getReservation(@PathVariable(name = "reservation_id") UUID reservationId) {
        try {
            final ReservationEntity reservation = reservationService.read(reservationId);

            return reservation != null
                    ? new ResponseEntity<>(reservation, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
