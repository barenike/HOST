package com.host.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "reservations")
public class ReservationEntity {
    @Id
    @Column(name = "reservation_id")
    private UUID reservationId; // Будем генерировать при создании объекта: UUID uuid = UUID.randomUUID();

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "table_id")
    private UUID tableId;

    @Column(name = "begin_date")
    private Date beginDate;

    @Column(name = "end_date")
    private Date endDate;

    public ReservationEntity(UUID reservationId, UUID userId, UUID tableId, Date beginDate, Date endDate) {
        this.reservationId = reservationId;
        this.userId = userId;
        this.tableId = tableId;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public ReservationEntity() {

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

    public UUID getTableId() {
        return tableId;
    }

    public void setTableId(UUID tableId) {
        this.tableId = tableId;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReservationEntity that = (ReservationEntity) o;

        return Objects.equals(reservationId, that.reservationId);
    }

    @Override
    public int hashCode() {
        return reservationId != null ? reservationId.hashCode() : 0;
    }
}
