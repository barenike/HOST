package com.host.controller;

import com.host.model.entity.ReservationEntity;
import com.host.model.repository.ReservationRepository;
import com.host.model.service.implementations.ReservationServiceImplementation;
import com.host.model.service.interfaces.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationRepository reservationRepository;
    private final ReservationService reservationService;

    public ReservationController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
        reservationService = new ReservationServiceImplementation(this.reservationRepository);
    }

    @PostMapping(value = "/reservations")
    public ResponseEntity<?> createReservation(@RequestBody ReservationEntity reservation) {
        try {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/reservations")
    public ResponseEntity<?> getReservations(@RequestBody ReservationEntity reservation) {
        try {
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/reservations/{reservation_id}")
    public ResponseEntity<?> deleteReservation(@RequestBody ReservationEntity reservation) {
        try {
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/reservations/{reservation_id}")
    public ResponseEntity<?> getReservation(@RequestBody ReservationEntity reservation) {
        try {
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
