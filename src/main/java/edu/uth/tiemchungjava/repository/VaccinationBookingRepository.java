package edu.uth.tiemchungjava.repository;

import edu.uth.tiemchungjava.models.VaccinationBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccinationBookingRepository extends JpaRepository<VaccinationBooking, Long> {
    // Các phương thức query tùy chọn có thể được thêm vào sau
}