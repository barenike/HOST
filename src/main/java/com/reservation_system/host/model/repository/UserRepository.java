package com.reservation_system.host.model.repository;

import com.reservation_system.host.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
// UUID, а не Long
/*public interface UserRepository extends CrudRepository<UserEntity, UUID> {
}*/

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

}
