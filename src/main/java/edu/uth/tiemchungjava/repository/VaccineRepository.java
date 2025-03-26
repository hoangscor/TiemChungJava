package edu.uth.tiemchungjava.repository;

import edu.uth.tiemchungjava.models.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VaccineRepository extends JpaRepository<Vaccine, Long> {
    List<Vaccine> findByAgeGroup(String ageGroup);
}