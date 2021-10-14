package com.host.bootstrap;

import com.host.model.repository.ReservationRepository;
import com.host.model.repository.TableRepository;
import com.host.model.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//test class?
@Component
public class BootStrapData implements CommandLineRunner {
    private final UserRepository userRepository;
    private final TableRepository tableRepository;
    private final ReservationRepository reservationRepository;

    public BootStrapData(UserRepository userRepository, TableRepository tableRepository, ReservationRepository reservationRepository) {
        this.userRepository = userRepository;
        this.tableRepository = tableRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void run(String... args) {

    }
}
