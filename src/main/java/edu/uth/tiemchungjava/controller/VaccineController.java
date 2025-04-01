package edu.uth.tiemchungjava.controller;

import edu.uth.tiemchungjava.dto.VaccineDTO;
import edu.uth.tiemchungjava.models.Vaccine;
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
    @GetMapping("/")
    public String showIndex(Model model) {
        List<VaccineDTO> vaccines = service.getAllVaccines();  // Lấy danh sách vaccine từ service
        model.addAttribute("vaccines", vaccines);  // Truyền vào model với tên "vaccines"
        return "index";  // Trả về view index
    }
    @GetMapping("/vaccines/new")
    public String createVaccineForm(Model model) {
        model.addAttribute("vaccine", new Vaccine());
        return "create_vaccine";
    }

    // Xử lý tạo mới vaccine
    @PostMapping("/vaccines")
    public String createVaccine(@ModelAttribute Vaccine vaccine) {
        service.createVaccine(vaccine);
        return "redirect:/vaccines"; // Redirect về trang danh sách vaccine
    }

    // Hiển thị trang chỉnh sửa vaccine
    @GetMapping("/vaccines/edit/{id}")
    public String editVaccineForm(@PathVariable Long id, Model model) {
        model.addAttribute("vaccine", service.getVaccineById(id).orElseThrow(() -> new RuntimeException("Vaccine not found")));
        return "edit_vaccine";
    }

    // Xử lý cập nhật vaccine
    @PostMapping("/vaccines/update/{id}")
    public String updateVaccine(@PathVariable Long id, @ModelAttribute Vaccine vaccine) {
        service.updateVaccine(id, vaccine);
        return "redirect:/vaccines"; // Redirect về trang danh sách vaccine
    }

    // Xử lý xóa vaccine
    @GetMapping("/vaccines/delete/{id}")
    public String deleteVaccine(@PathVariable Long id) {
        service.deleteVaccine(id);
        return "redirect:/vaccines"; // Redirect về trang danh sách vaccine
    }
}


