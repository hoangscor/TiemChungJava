package edu.uth.tiemchungjava.controller;

import edu.uth.tiemchungjava.models.Payment;
import edu.uth.tiemchungjava.models.MyUser;
import edu.uth.tiemchungjava.models.VaccinationBooking;
import edu.uth.tiemchungjava.repository.PaymentRepository;
import edu.uth.tiemchungjava.repository.VaccinationBookingRepository;
import edu.uth.tiemchungjava.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class OrderHistoryController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/history")
    public String showPaymentHistory(Model model) {
        // Lấy tất cả Payment
        List<Payment> payments = paymentService.getAllPayments();
        model.addAttribute("payments", payments);
        return "paymentHistory";

    }
}