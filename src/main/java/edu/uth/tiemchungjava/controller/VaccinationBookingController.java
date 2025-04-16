package edu.uth.tiemchungjava.controller;

import edu.uth.tiemchungjava.models.VaccinationBooking;
import edu.uth.tiemchungjava.models.Vaccine;
import edu.uth.tiemchungjava.repository.VaccineRepository;
import edu.uth.tiemchungjava.service.VaccinationBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/datlichtiem")
public class VaccinationBookingController {

    @Autowired
    private VaccinationBookingService bookingService;

    @Autowired
    private VaccineRepository vaccineRepository;

    // API tạo booking (thêm thông tin đặt lịch tiêm)
    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<?> createBooking(@RequestBody VaccinationBooking booking) {
        try {
            // Lưu thông tin đặt lịch tiêm vào cơ sở dữ liệu
            VaccinationBooking savedBooking = bookingService.createBooking(booking);

            Map<String, Object> response = new HashMap<>();
            response.put("id", savedBooking.getId());
            response.put("status", "success");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Đã xảy ra lỗi khi đặt lịch: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    // API hủy booking
    @PostMapping("/cancel/{id}")
    public ResponseEntity<?> cancelBooking(@PathVariable("id") Long id) {
        try {
            Optional<VaccinationBooking> bookingOpt = bookingService.getBookingById(id);

            if (!bookingOpt.isPresent()) {
                Map<String, String> response = new HashMap<>();
                response.put("error", "Không tìm thấy thông tin đặt lịch");
                return ResponseEntity.badRequest().body(response);
            }

            VaccinationBooking booking = bookingOpt.get();
            booking.setStatus("CANCELLED");
            booking.setPaymentStatus("CANCELLED");

            bookingService.updateBooking(booking);

            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Đã hủy đặt lịch tiêm thành công");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Đã xảy ra lỗi khi hủy đặt lịch: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    // Hiển thị form đặt lịch tiêm với danh sách vaccine
    @GetMapping
    public String showBookingForm(Model model) {
        List<Vaccine> vaccineList = vaccineRepository.findAll();
        model.addAttribute("vaccines", vaccineList);

        return "giaodiendatlichtiem.html";
    }
}