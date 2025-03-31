package edu.uth.tiemchungjava.service;

import edu.uth.tiemchungjava.models.VaccinationBooking;
import edu.uth.tiemchungjava.repository.VaccinationBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VaccinationBookingService {
    @Autowired
    private VaccinationBookingRepository repository;

    public VaccinationBooking createBooking(VaccinationBooking booking) {
        return repository.save(booking);
    }
}