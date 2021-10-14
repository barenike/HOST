package com.host.repository;

import com.host.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
// UUID, а не Long
/*public interface UserRepository extends CrudRepository<UserEntity, UUID> {
}*/

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

}
