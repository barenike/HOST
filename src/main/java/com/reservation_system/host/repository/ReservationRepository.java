package com.reservation_system.host.repository;

import com.reservation_system.host.entity.ReservationEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;
// UUID, а не Long
public interface ReservationRepository extends CrudRepository<ReservationEntity, UUID> {
}
