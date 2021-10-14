package com.host.model.service.interfaces;

import com.host.model.entity.TableEntity;

import java.util.List;
import java.util.UUID;

public interface TableService {

    void create(TableEntity table);
    List<TableEntity> readAll();
    TableEntity read(UUID id);
    boolean update(TableEntity table, UUID id);
    boolean delete(UUID id);
}
