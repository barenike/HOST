package com.reservation_system.host.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "tables")
public class TableEntity {

    @Id
    @Column(unique = true, name = "table_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tableId;

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

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
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
}
