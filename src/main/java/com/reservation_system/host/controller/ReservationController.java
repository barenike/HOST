package com.reservation_system.host.controller;

import com.reservation_system.host.configuration.jwt.JwtProvider;
import com.reservation_system.host.infrastructure.AdminReservationRequest;
import com.reservation_system.host.infrastructure.ReservationRequest;
import com.reservation_system.host.model.entity.ReservationEntity;
import com.reservation_system.host.model.entity.TableEntity;
import com.reservation_system.host.model.entity.TableStatusEnum;
import com.reservation_system.host.model.service.ReservationService;
import com.reservation_system.host.model.service.TableService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class ReservationController {

    private final JwtProvider jwtProvider;

    private final ReservationService reservationService;

    private final TableService tableService;

    public ReservationController(ReservationService reservationService, TableService tableService, JwtProvider jwtProvider) {
        this.reservationService = reservationService;
        this.tableService = tableService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping(value = "/user/reservations")
    public ResponseEntity<HttpStatus> createMyReservation(
            @RequestBody ReservationRequest reservationRequest,
            @RequestHeader(name = "Authorization") String token
    ) {
        try {
            ReservationEntity reservation = new ReservationEntity();

            String userId = jwtProvider.getUserIdFromToken(token.substring(7));
            reservation.setUserId(UUID.fromString(userId));

            return createReservation(reservation,
                    reservationRequest.getTableId(),
                    reservationRequest.getBeginDate(),
                    reservationRequest.getBeginTime(),
                    reservationRequest.getEndDate(),
                    reservationRequest.getEndTime());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/admin/reservations")
    public ResponseEntity<HttpStatus> createReservation(@ModelAttribute AdminReservationRequest adminReservationRequest) {
        try {
            ReservationEntity reservation = new ReservationEntity();

            reservation.setUserId(adminReservationRequest.getUserId());

            return createReservation(reservation,
                    adminReservationRequest.getTableId(),
                    adminReservationRequest.getBeginDate(),
                    adminReservationRequest.getBeginTime(),
                    adminReservationRequest.getEndDate(),
                    adminReservationRequest.getEndTime());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private ResponseEntity<HttpStatus> createReservation(ReservationEntity reservation,
                                                         Integer tableId,
                                                         String beginDate2,
                                                         String beginTime,
                                                         String endDate2,
                                                         String endTime) throws ParseException {
        reservation.setTableId(tableId);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);

        Date beginDate = formatter.parse(String.format("%s %s", beginDate2, beginTime));
        Date endDate = formatter.parse(String.format("%s %s", endDate2, endTime));

        if (beginDate.getTime() > endDate.getTime()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (isWrongDate(beginDate) || isWrongDate(endDate)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        reservation.setBeginDate(beginDate);
        reservation.setEndDate(endDate);

        ResponseEntity<HttpStatus> response = checkTableAvailability(tableId, beginDate, endDate);
        if (response != null) {
            return response;
        }

        reservationService.createReservation(reservation);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private boolean isWrongDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        if (dayOfWeek == 1 || dayOfWeek == 7) {
            return true;
        } else {
            int hours = Integer.parseInt(new SimpleDateFormat("HH").format(date));
            return hours < 8 || hours > 21;
        }
    }

    private ResponseEntity<HttpStatus> checkTableAvailability(Integer tableId, Date beginDate, Date endDate) {
        final List<TableEntity> tables = tableService.readAll();
        if (tables == null || tables.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Map<Integer, TableStatusEnum> tableMap = tableService.getTablesWithStatus(tables, beginDate, endDate);
        if (!tableMap.get(tableId).equals(TableStatusEnum.AVAILABLE)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    @GetMapping(value = "/user/reservations")
    public ResponseEntity<List<ReservationEntity>> getMyReservations(@RequestHeader(name = "Authorization") String token) {
        try {
            String userId = jwtProvider.getUserIdFromToken(token.substring(7));
            final List<ReservationEntity> targetedReservations = reservationService.getMyReservations(userId);

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
            @RequestHeader(name = "Authorization") String token
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
    public ResponseEntity<Optional<ReservationEntity>> getMyReservation(
            @PathVariable(name = "reservation_id") UUID reservationId,
            @RequestHeader(name = "Authorization") String token
    ) {
        try {
            String userId = jwtProvider.getUserIdFromToken(token.substring(7));
            final Optional<ReservationEntity> reservation = reservationService.getMyReservationById(reservationId, userId);
            return reservation.isPresent()
                    ? new ResponseEntity<>(reservation, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (AccessDeniedException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/admin/reservations/{reservation_id}")
    public ResponseEntity<Optional<ReservationEntity>> getReservation(@PathVariable(name = "reservation_id") UUID reservationId) {
        try {
            final Optional<ReservationEntity> reservation = reservationService.getOptionalReservationById(reservationId);

            return reservation.isPresent()
                    ? new ResponseEntity<>(reservation, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
