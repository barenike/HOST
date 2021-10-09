package com.reservation_system.host.repository;

import com.reservation_system.host.entity.TableEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;
// UUID, а не Long
public interface TableRepository extends CrudRepository<TableEntity, UUID> {
}
