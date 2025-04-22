package edu.uth.tiemchungjava.repository;

import edu.uth.tiemchungjava.models.VaccinationBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VaccinationBookingRepository extends JpaRepository<VaccinationBooking, Long> {
    List<VaccinationBooking> findByBookingDateBetween(LocalDate start, LocalDate end);
}