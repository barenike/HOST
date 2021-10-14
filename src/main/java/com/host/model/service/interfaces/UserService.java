package com.host.model.service.interfaces;

import com.host.model.entity.UserEntity;

import java.util.List;
import java.util.UUID;

public interface UserService {

    void create(UserEntity user);
    List<UserEntity> readAll();
    UserEntity read(UUID id);
    boolean update(UserEntity user, UUID id);
    boolean delete(UUID id);
}
