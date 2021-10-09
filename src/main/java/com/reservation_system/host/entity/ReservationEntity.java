package com.reservation_system.host.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

public class ReservationEntity {
    // Не факт, что будет работать код с 12 строки по 17
    @Id
    @GeneratedValue(generator = "uuid")
    // name - название перемнной к которой мы хотим обратиться в БД?
    // strategy - см. в Google
    @GenericGenerator(name = "reservation_id", strategy = "uuid2")
    private UUID reservation_uuid;
    private UUID user_UUID;
    private UUID table_UUID;
    private Date beginDate;
    private Date endDate;

    public ReservationEntity(UUID reservation_uuid, UUID user_UUID, UUID table_UUID, Date beginDate, Date endDate) {
        this.reservation_uuid = reservation_uuid;
        this.user_UUID = user_UUID;
        this.table_UUID = table_UUID;
        this.beginDate = beginDate;
        this.endDate = endDate;
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
}
