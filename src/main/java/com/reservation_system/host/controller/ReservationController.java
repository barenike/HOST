package com.reservation_system.host.controller;

import com.reservation_system.host.configuration.jwt.JwtProvider;
import com.reservation_system.host.infrastructure.ReservationRequest;
import com.reservation_system.host.model.entity.ReservationEntity;
import com.reservation_system.host.model.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ReservationController {

    @Autowired
    private JwtProvider jwtProvider;

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping(value = "/user/reservations")
    public ResponseEntity<HttpStatus> createMyReservation(
            @RequestBody ReservationRequest reservationRequest,
            @RequestHeader (name = "Authorization") String token
    ) {
        try {
            String userId = jwtProvider.getUserIdFromToken(token.substring(7));
            ReservationEntity reservation = new ReservationEntity();
            reservation.setUserId(UUID.fromString(userId));
            reservation.setTableId(reservationRequest.getTableId());
            reservation.setBeginDate(reservationRequest.getBeginDate());
            reservation.setEndDate(reservationRequest.getEndDate());
            reservationService.createReservation(reservation);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/admin/reservations")
    public ResponseEntity<HttpStatus> createReservation(@RequestBody ReservationEntity reservation) {
        try {
            reservationService.createReservation(reservation);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/user/reservations")
    public ResponseEntity<List<ReservationEntity>> getMyReservations(@RequestHeader (name = "Authorization") String token) {
        try {
            final List<ReservationEntity> targetedReservations = reservationService.getMyReservations(token.substring(7));

            return !targetedReservations.isEmpty()
                    ? new ResponseEntity<>(targetedReservations, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/admin/reservations")
    public ResponseEntity<List<ReservationEntity>> getReservations() {
        try {
            final List<ReservationEntity> reservations = reservationService.getAllReservations();

            return reservations != null && !reservations.isEmpty()
                    ? new ResponseEntity<>(reservations, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/user/reservations/{reservation_id}")
    public ResponseEntity<HttpStatus> deleteMyReservation(
            @PathVariable(name = "reservation_id") UUID reservationId,
            @RequestHeader (name = "Authorization") String token
    ) {
        try {
            String userId = jwtProvider.getUserIdFromToken(token.substring(7));
            final boolean isDeleted = reservationService.deleteMyReservation(reservationId, userId);

            return isDeleted
                    ? new ResponseEntity<>(HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        } catch (AccessDeniedException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/admin/reservations/{reservation_id}")
    public ResponseEntity<HttpStatus> deleteReservation(@PathVariable(name = "reservation_id") UUID reservationId) {
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
    public ResponseEntity<ReservationEntity> getMyReservation(
            @PathVariable(name = "reservation_id") UUID reservationId,
            @RequestHeader (name = "Authorization") String token
    ) {
        try {
            String userId = jwtProvider.getUserIdFromToken(token.substring(7));
            final ReservationEntity reservation = reservationService.getMyReservationById(reservationId, userId);
            return reservation != null
                    ? new ResponseEntity<>(reservation, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (AccessDeniedException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/admin/reservations/{reservation_id}")
    public ResponseEntity<ReservationEntity> getReservation(@PathVariable(name = "reservation_id") UUID reservationId) {
        try {
            final ReservationEntity reservation = reservationService.getReservationById(reservationId);

            return reservation != null
                    ? new ResponseEntity<>(reservation, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
