package edu.uth.tiemchungjava.controller;

import edu.uth.tiemchungjava.dto.VaccineDTO;
import edu.uth.tiemchungjava.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vaccines")
@CrossOrigin(origins = "*")
public class VaccineController {
    @Autowired
    private VaccineService service;

    @GetMapping
    public List<VaccineDTO> getAll() {
        return service.getAllVaccines();
    }

    @GetMapping("/age-group/{group}")
    public List<VaccineDTO> getByGroup(@PathVariable String group) {
        return service.getByAgeGroup(group);
    }
}