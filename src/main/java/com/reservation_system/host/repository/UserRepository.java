package com.reservation_system.host.repository;

import com.reservation_system.host.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;
// UUID, а не Long
public interface UserRepository extends CrudRepository<UserEntity, UUID> {
}
