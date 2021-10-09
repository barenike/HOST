package com.reservation_system.host.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

public class TableEntity {
    // Не факт, что будет работать код с 12 строки по 17
    @Id
    @GeneratedValue(generator = "uuid")
    // name - название перемнной к которой мы хотим обратиться в БД?
    // strategy - см. в Google
    @GenericGenerator(name = "table_id", strategy = "uuid2")
    private UUID table_uuid;
    private boolean isAvailable;
    private String description;

    public TableEntity(UUID table_uuid, boolean isAvailable) {
        this.table_uuid = table_uuid;
        this.isAvailable = isAvailable;
    }

    public TableEntity(UUID table_uuid, boolean isAvailable, String description) {
        this.table_uuid = table_uuid;
        this.isAvailable = isAvailable;
        this.description = description;
    }

    public UUID getTable_uuid() {
        return table_uuid;
    }

    public void setTable_uuid(UUID table_uuid) {
        this.table_uuid = table_uuid;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
