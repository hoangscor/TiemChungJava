package edu.uth.tiemchungjava.service;

import edu.uth.tiemchungjava.dto.VaccineDTO;
import edu.uth.tiemchungjava.repository.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VaccineService {
    @Autowired
    private VaccineRepository repository;

    public List<VaccineDTO> getAllVaccines() {
        return repository.findAll().stream()
                .map(v -> new VaccineDTO(v.getName(), v.getDescription(), v.getAgeGroup(), v.getPrice()))
                .collect(Collectors.toList());
    }

    public List<VaccineDTO> getByAgeGroup(String ageGroup) {
        return repository.findByAgeGroup(ageGroup).stream()
                .map(v -> new VaccineDTO(v.getName(), v.getDescription(), v.getAgeGroup(), v.getPrice()))
                .collect(Collectors.toList());
    }
}