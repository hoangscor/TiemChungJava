package edu.uth.tiemchungjava.repository;

import edu.uth.tiemchungjava.models.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    // Các phương thức tùy chỉnh nếu cần
}