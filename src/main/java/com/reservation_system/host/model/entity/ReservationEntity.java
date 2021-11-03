package com.reservation_system.host.model.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "reservations")
public class ReservationEntity {

    @Id
    @Column(unique = true, name = "reservation_id", nullable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private UUID reservationId;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "table_id", nullable = false)
    private Integer tableId;

    @Column(name = "begin_date", nullable = false)
    private Timestamp beginDate;

    @Column(name = "end_date", nullable = false)
    private Timestamp endDate;

    public ReservationEntity() {
    }

    public ReservationEntity(UUID userId, Integer tableId, Timestamp beginDate, Timestamp endDate) {
        this.userId = userId;
        this.tableId = tableId;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public UUID getReservationId() {
        return reservationId;
    }

    public void setReservationId(UUID reservationId) {
        this.reservationId = reservationId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public Timestamp getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Timestamp beginDate) {
        this.beginDate = beginDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }
}
