package com.reservation_system.host.model.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tables")
public class TableEntity {

    @Id
    @Column(unique = true, name = "table_id", nullable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private UUID tableId;

    @Column(name = "is_available", nullable = false)
    private boolean isAvailable;

    @Column(name = "description")
    private String description;

    public TableEntity(boolean isAvailable, String description) {
        this.isAvailable = isAvailable;
        this.description = description;
    }

    public TableEntity() {

    }

    public UUID getTableId() {
        return tableId;
    }

    public void setTableId(UUID tableId) {
        this.tableId = tableId;
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

    //Пусть будет - пригодится
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TableEntity that = (TableEntity) o;

        return Objects.equals(tableId, that.tableId);
    }

    @Override
    public int hashCode() {
        return tableId != null ? tableId.hashCode() : 0;
    }
}
