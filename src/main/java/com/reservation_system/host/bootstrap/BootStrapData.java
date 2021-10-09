package com.reservation_system.host.bootstrap;

import com.reservation_system.host.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//test class?
@Component
public class BootStrapData implements CommandLineRunner {
    private final UserRepository userRepository;

    public BootStrapData(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
