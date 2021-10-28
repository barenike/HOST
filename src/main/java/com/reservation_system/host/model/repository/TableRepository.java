package com.reservation_system.host.model.repository;

import com.reservation_system.host.model.entity.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TableRepository extends JpaRepository<TableEntity, Integer> {

}
