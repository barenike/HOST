package com.reservation_system.host.model.repository;

import com.reservation_system.host.model.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
// UUID, а не Long
/*public interface ReservationRepository extends CrudRepository<ReservationEntity, UUID> {
}*/

public interface ReservationRepository extends JpaRepository<ReservationEntity, UUID> {

}
