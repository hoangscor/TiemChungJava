package edu.uth.tiemchungjava.service;

import edu.uth.tiemchungjava.dto.VaccineDTO;
import edu.uth.tiemchungjava.models.Vaccine;
import edu.uth.tiemchungjava.repository.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VaccineService {

    @Autowired
    private VaccineRepository repository;

    public List<VaccineDTO> getAllVaccines() {
        return repository.findAllByOrderByAgeGroupAscNameAsc()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<VaccineDTO> getByAgeGroup(String ageGroup) {
        return repository.findByAgeGroup(ageGroup)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private VaccineDTO convertToDTO(Vaccine v) {
        return new VaccineDTO(
                v.getName(),
                v.getDescription(),
                v.getAgeGroup(),
                v.getPrice() != null ? v.getPrice().intValue() : 0,
                v.getImg_url()
        );
    }
    public Vaccine createVaccine(Vaccine vaccine) {
        return repository.save(vaccine);
    }
    public Vaccine updateVaccine(Long id, Vaccine vaccineDetails) {
        Vaccine vaccine = repository.findById(id).orElseThrow(() -> new RuntimeException("Vaccine not found"));
        vaccine.setName(vaccineDetails.getName());
        vaccine.setDescription(vaccineDetails.getDescription());
        vaccine.setPrice(vaccineDetails.getPrice());
        vaccine.setImg_url(vaccineDetails.getImg_url());
        return repository.save(vaccine);
    }
    public void deleteVaccine(Long id) {
        repository.deleteById(id);
    }
    // Lấy vaccine theo id
    public Optional<Vaccine> getVaccineById(Long id) {
        return repository.findById(id);
    }
}
