package com.host.repository;

import com.host.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
// UUID, а не Long
/*public interface ReservationRepository extends CrudRepository<ReservationEntity, UUID> {
}*/

public interface ReservationRepository extends JpaRepository<ReservationEntity, UUID> {

}
