package com.reservation_system.host.model.service.implementations;

import com.reservation_system.host.model.entity.UserEntity;
import com.reservation_system.host.model.repository.UserRepository;
import com.reservation_system.host.model.service.interfaces.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void create(UserEntity user) {
        userRepository.save(user);
    }

    @Override
    public List<UserEntity> readAll() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity read(UUID id) {
        return userRepository.getById(id);
    }

    @Override
    public boolean update(UserEntity user, UUID id) {
        if (userRepository.existsById(id)) {
            user.setUserId(id);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(UUID id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
