package edu.uth.tiemchungjava.controller;

import edu.uth.tiemchungjava.models.Payment;
import edu.uth.tiemchungjava.models.VaccinationBooking;
import edu.uth.tiemchungjava.models.Vaccine;
import edu.uth.tiemchungjava.repository.VaccineRepository;
import edu.uth.tiemchungjava.service.PaymentService;
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
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private VaccinationBookingService bookingService;

    @Autowired
    private VaccineRepository vaccineRepository;

    // API xử lý thanh toán
    @PostMapping("/process")
    @ResponseBody
    public ResponseEntity<?> processPayment(@RequestBody Map<String, Object> paymentData) {
        try {
            Long bookingId = Long.parseLong(paymentData.get("bookingId").toString());
            Optional<VaccinationBooking> bookingOpt = bookingService.getBookingById(bookingId);
            if (!bookingOpt.isPresent()) {
                Map<String, String> response = new HashMap<>();
                response.put("error", "Không tìm thấy thông tin đặt lịch");
                return ResponseEntity.badRequest().body(response);
            }
            VaccinationBooking booking = bookingOpt.get();

            // Lấy thông tin vaccine từ booking: nếu đã có, dùng luôn; nếu chưa có, tra cứu theo vaccineType
            Vaccine selectedVaccine = booking.getVaccine();
            if (selectedVaccine == null && booking.getVaccineType() != null) {
                try {
                    Long vaccineId = Long.parseLong(booking.getVaccineType());
                    selectedVaccine = vaccineRepository.findById(vaccineId).orElse(null);
                } catch(Exception e) {
                    // Log lỗi nếu cần, ví dụ: logger.error("Lỗi chuyển đổi vaccineId: ", e);
                }
            }

            if (selectedVaccine == null) {
                Map<String, String> response = new HashMap<>();
                response.put("error", "Thông tin vaccine không hợp lệ");
                return ResponseEntity.badRequest().body(response);
            }

            // Sử dụng giá của vaccine làm số tiền thanh toán
            double price = selectedVaccine.getPrice();

            // Kiểm tra thông tin thẻ
            String cardNumber = (String) paymentData.get("cardNumber");
            String cardName = (String) paymentData.get("cardName");
            String expiryDate = (String) paymentData.get("expiryDate");
            boolean isValidCard = paymentService.validateCardInfo(cardNumber, cardName, expiryDate);
            if (!isValidCard) {
                Map<String, String> response = new HashMap<>();
                response.put("error", "Thông tin thẻ không hợp lệ");
                return ResponseEntity.badRequest().body(response);
            }

            // Tạo đối tượng Payment và gán số tiền lấy từ vaccine (không dùng giá trị truyền lên từ client)
            Payment payment = new Payment();
            payment.setBooking(booking);
            payment.setAmount(price); // Sử dụng giá của vaccine
            payment.setCardNumber(cardNumber.substring(cardNumber.length() - 4)); // Lưu 4 số cuối của thẻ
            payment.setStatus("COMPLETED");

            paymentService.savePayment(payment);

            // Cập nhật trạng thái đặt lịch
            booking.setPaymentStatus("PAID");
            bookingService.updateBooking(booking);

            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Thanh toán thành công");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Đã xảy ra lỗi khi xử lý thanh toán: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    // Hiển thị trang thanh toán với thông tin booking
    @GetMapping
    public String showPaymentPage(@RequestParam("bookingId") Long bookingId, Model model) {
        Optional<VaccinationBooking> bookingOpt = bookingService.getBookingById(bookingId);

        if (!bookingOpt.isPresent()) {
            return "redirect:/datlichtiem";
        }

        VaccinationBooking booking = bookingOpt.get();
        model.addAttribute("booking", booking);
        Vaccine selectedVaccine = booking.getVaccine();
        if (selectedVaccine == null && booking.getVaccineType() != null) {
            try {
                Long vaccineId = Long.parseLong(booking.getVaccineType());
                selectedVaccine = vaccineRepository.findById(vaccineId).orElse(null);
            } catch(Exception e) {
                // Nếu chuyển đổi thất bại hoặc không tìm thấy vaccine, để null
            }
        }
// Đưa đối tượng vaccine vào model
        model.addAttribute("vaccine", selectedVaccine);

        return "order.html";
    }
}