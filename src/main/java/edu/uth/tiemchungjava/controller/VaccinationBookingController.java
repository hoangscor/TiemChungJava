package edu.uth.tiemchungjava.controller;

import edu.uth.tiemchungjava.models.VaccinationBooking;
import edu.uth.tiemchungjava.models.Vaccine;
import edu.uth.tiemchungjava.repository.VaccineRepository;
import edu.uth.tiemchungjava.service.VaccinationBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/datlichtiem")
public class VaccinationBookingController {

    @Autowired
    private VaccinationBookingService bookingService;

    @Autowired
    private VaccineRepository vaccineRepository;

    // API tạo booking (thêm thông tin đặt lịch tiêm)
    @PostMapping("/create")
    public String createBooking(@RequestBody VaccinationBooking booking) {
        // Lưu thông tin đặt lịch tiêm vào cơ sở dữ liệu
        bookingService.createBooking(booking);
        return "redirect:/datlichtiem";  // Sau khi lưu xong, chuyển hướng về trang đặt lịch tiêm
    }

    // Hiển thị form đặt lịch tiêm với danh sách vaccine
    @GetMapping
    public String showBookingForm(Model model) {
        List<Vaccine> vaccineList = vaccineRepository.findAll();
        model.addAttribute("vaccines", vaccineList);

        return "giaodiendatlichtiem.html";
    }
}
