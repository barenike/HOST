package com.reservation_system.host.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tables")
public class TableEntity {
    @Id
    @Column(name = "table_id")
    private UUID table_uuid; // Будем генерировать при создании объекта: UUID uuid = UUID.randomUUID();

    @Column(name = "is_available")
    private boolean isAvailable;

    @Column(name = "description")
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

    public TableEntity() {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TableEntity that = (TableEntity) o;

        return Objects.equals(table_uuid, that.table_uuid);
    }

    @Override
    public int hashCode() {
        return table_uuid != null ? table_uuid.hashCode() : 0;
    }
}
