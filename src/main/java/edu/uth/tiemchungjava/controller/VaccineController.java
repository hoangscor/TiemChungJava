package edu.uth.tiemchungjava.controller;

import edu.uth.tiemchungjava.dto.VaccineDTO;
import edu.uth.tiemchungjava.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/vaccines")
public class VaccineController {
    @Autowired
    private VaccineService service;

    @GetMapping
    public String showVaccines(Model model) {
        List<VaccineDTO> vaccines = service.getAllVaccines();
        model.addAttribute("vaccines", vaccines);
        return "vaccine";
    }
}
