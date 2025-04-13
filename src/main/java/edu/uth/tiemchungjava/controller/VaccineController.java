package edu.uth.tiemchungjava.controller;

import edu.uth.tiemchungjava.dto.VaccineDTO;
import edu.uth.tiemchungjava.models.Vaccine;
import edu.uth.tiemchungjava.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/vaccines") // Base path: /vaccines
public class VaccineController {

    @Autowired
    private VaccineService service;

    // Hiển thị danh sách tất cả vaccine
    @GetMapping
    public String showVaccines(Model model) {
        List<VaccineDTO> vaccines = service.getAllVaccines();
        model.addAttribute("vaccines", vaccines);
        return "vaccine";
    }

    // Hiển thị trang chủ
    @GetMapping("/index")
    public String showIndex(Model model) {
        List<VaccineDTO> vaccines = service.getAllVaccines();
        model.addAttribute("vaccines", vaccines);
        return "index";
    }

    // Hiển thị form tạo vaccine mới
    @GetMapping("/new")
    public String createVaccineForm(Model model) {
        model.addAttribute("vaccine", new Vaccine());
        return "create_vaccine";
    }

    // Xử lý tạo mới vaccine
    @PostMapping
    public String createVaccine(@ModelAttribute Vaccine vaccine) {
        service.createVaccine(vaccine);
        return "redirect:/vaccines";
    }

    // Hiển thị form chỉnh sửa vaccine
    @GetMapping("/edit/{id}")
    public String editVaccineForm(@PathVariable("id") Long id, Model model) {
        Vaccine vaccine = service.getVaccineById(id)
                .orElseThrow(() -> new RuntimeException("Vaccine not found"));
        model.addAttribute("vaccine", vaccine);
        return "edit_vaccine";
    }

    // Xử lý cập nhật vaccine
    @PostMapping("/update/{id}")
    public String updateVaccine(@PathVariable("id") Long id, @ModelAttribute Vaccine vaccine) {
        service.updateVaccine(id, vaccine);
        return "redirect:/vaccines";
    }

    // Xử lý xóa vaccine
    @GetMapping("/delete/{id}")
    public String deleteVaccine(@PathVariable("id") Long id) {
        service.deleteVaccine(id);
        return "redirect:/vaccines";
    }

    // ✅ Trang đặt hàng cho vaccine
    @GetMapping("/tao-don-hang/{id}")
    public String taoDonHang(@PathVariable("id") Long id, Model model) {
        Vaccine vaccine = service.getVaccineById(id)
                .orElseThrow(() -> new RuntimeException("Vaccine not found"));
        model.addAttribute("vaccine", vaccine);
        return "order"; // phải có file order.html trong templates
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getVaccineById(@PathVariable("id") Long id) {
        Optional<Vaccine> vaccineOpt = service.getVaccineById(id);
        if (vaccineOpt.isPresent()) {
            return ResponseEntity.ok(vaccineOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
