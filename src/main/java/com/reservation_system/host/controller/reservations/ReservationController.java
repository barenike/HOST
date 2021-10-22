package com.reservation_system.host.controller.reservations;

import com.reservation_system.host.model.entity.ReservationEntity;
import com.reservation_system.host.model.repository.ReservationRepository;
import com.reservation_system.host.model.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationRepository reservationRepository) {
        reservationService = new ReservationService(reservationRepository);
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

    @DeleteMapping(value = "/reservations/{reservation_id}")
    public ResponseEntity<?> deleteReservation(@PathVariable(name = "reservation_id") UUID id) {
        try {
            final boolean isDeleted = reservationService.delete(id);

            return isDeleted
                    ? new ResponseEntity<>(HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/reservations/{reservation_id}")
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
