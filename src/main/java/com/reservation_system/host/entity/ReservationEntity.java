package com.reservation_system.host.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "reservations")
public class ReservationEntity {
    @Id
    @Column(name = "reservation_id")
    private UUID reservation_uuid; // Будем генерировать при создании объекта: UUID uuid = UUID.randomUUID();

    @Column(name = "user_id")
    private UUID user_UUID;

    @Column(name = "table_id")
    private UUID table_UUID;

    @Column(name = "begin_date")
    private Date beginDate;

    @Column(name = "end_date")
    private Date endDate;

    public ReservationEntity(UUID reservation_uuid, UUID user_UUID, UUID table_UUID, Date beginDate, Date endDate) {
        this.reservation_uuid = reservation_uuid;
        this.user_UUID = user_UUID;
        this.table_UUID = table_UUID;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public ReservationEntity() {

    }

    public UUID getReservation_uuid() {
        return reservation_uuid;
    }

    public void setReservation_uuid(UUID reservation_uuid) {
        this.reservation_uuid = reservation_uuid;
    }

    public UUID getUser_UUID() {
        return user_UUID;
    }

    public void setUser_UUID(UUID user_UUID) {
        this.user_UUID = user_UUID;
    }

    public UUID getTable_UUID() {
        return table_UUID;
    }

    public void setTable_UUID(UUID table_UUID) {
        this.table_UUID = table_UUID;
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

        return Objects.equals(reservation_uuid, that.reservation_uuid);
    }

    @Override
    public int hashCode() {
        return reservation_uuid != null ? reservation_uuid.hashCode() : 0;
    }
}
