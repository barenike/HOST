package com.host.service.interfaces;

import com.host.entity.UserEntity;

import java.util.List;
import java.util.UUID;

public interface UserService {

    public void create(UserEntity user);
    public List<UserEntity> readAll();
    public UserEntity read(UUID id);
    public boolean update(UserEntity user, UUID id);
    public boolean delete(UUID id);
}
