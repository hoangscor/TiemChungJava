package edu.uth.tiemchungjava.repository;

import edu.uth.tiemchungjava.models.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface VaccineRepository extends JpaRepository<Vaccine, Long> {

    // Lấy danh sách theo nhóm tuổi cụ thể
    List<Vaccine> findByAgeGroup(String ageGroup);

    // Lấy tất cả và sắp xếp theo nhóm tuổi -> tên vắc xin
    List<Vaccine> findAllByOrderByAgeGroupAscNameAsc();
    @Query("SELECT DISTINCT v.ageGroup FROM Vaccine v")
    List<String> findDistinctAgeGroups();
}