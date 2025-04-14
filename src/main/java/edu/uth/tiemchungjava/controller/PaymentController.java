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
            String cardNumber = (String) paymentData.get("cardNumber");
            String cardName = (String) paymentData.get("cardName");
            String expiryDate = (String) paymentData.get("expiryDate");
            double amount = Double.parseDouble(paymentData.get("amount").toString());

            // Kiểm tra xem booking có tồn tại không
            Optional<VaccinationBooking> bookingOpt = bookingService.getBookingById(bookingId);
            if (!bookingOpt.isPresent()) {
                Map<String, String> response = new HashMap<>();
                response.put("error", "Không tìm thấy thông tin đặt lịch");
                return ResponseEntity.badRequest().body(response);
            }

            // Kiểm tra thông tin thẻ (điều này nên được thực hiện bởi cổng thanh toán thực tế)
            boolean isValidCard = paymentService.validateCardInfo(cardNumber, cardName, expiryDate);
            if (!isValidCard) {
                Map<String, String> response = new HashMap<>();
                response.put("error", "Thông tin thẻ không hợp lệ");
                return ResponseEntity.badRequest().body(response);
            }

            // Lưu thông tin thanh toán
            Payment payment = new Payment();
            payment.setBooking(bookingOpt.get());
            payment.setAmount(amount);
            payment.setCardNumber(cardNumber.substring(cardNumber.length() - 4)); // Chỉ lưu 4 số cuối
            payment.setStatus("COMPLETED");

            paymentService.savePayment(payment);

            // Cập nhật trạng thái đặt lịch
            VaccinationBooking booking = bookingOpt.get();
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