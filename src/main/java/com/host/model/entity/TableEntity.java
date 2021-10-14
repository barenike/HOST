package com.host.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tables")
public class TableEntity {
    @Id
    @Column(name = "table_id")
    private UUID tableId; // Будем генерировать при создании объекта: UUID uuid = UUID.randomUUID();

    @Column(name = "is_available")
    private boolean isAvailable;

    @Column(name = "description")
    private String description;

    public TableEntity(UUID tableId, boolean isAvailable) {
        this.tableId = tableId;
        this.isAvailable = isAvailable;
    }

    public TableEntity(UUID tableId, boolean isAvailable, String description) {
        this.tableId = tableId;
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
