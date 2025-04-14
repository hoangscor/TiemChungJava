package edu.uth.tiemchungjava.repository;

import edu.uth.tiemchungjava.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // Có thể thêm các phương thức truy vấn tùy chỉnh nếu cần
}