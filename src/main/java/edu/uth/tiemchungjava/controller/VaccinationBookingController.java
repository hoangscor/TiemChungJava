package edu.uth.tiemchungjava.controller;

import edu.uth.tiemchungjava.models.VaccinationBooking;
import edu.uth.tiemchungjava.service.VaccinationBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/booking")
public class VaccinationBookingController {
    @Autowired
    private VaccinationBookingService bookingService;

    @PostMapping("/create")
    public ResponseEntity<VaccinationBooking> createBooking(@RequestBody VaccinationBooking booking) {
        VaccinationBooking savedBooking = bookingService.createBooking(booking);
        return ResponseEntity.ok(savedBooking);
    }
}