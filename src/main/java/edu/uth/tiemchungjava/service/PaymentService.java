package edu.uth.tiemchungjava.service;

import edu.uth.tiemchungjava.models.Payment;
import edu.uth.tiemchungjava.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    // Danh sách thẻ hợp lệ (trong thực tế sẽ được lưu trong database)
    private static final List<String[]> VALID_CARDS = Arrays.asList(
            // format: số thẻ, tên chủ thẻ, ngày hết hạn
            new String[]{"9704123456789012", "NGUYEN VAN A", "12/25"},
            new String[]{"9704223344556677", "TRAN THI B", "05/26"}
    );

    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }


    public boolean validateCardInfo(String cardNumber, String cardName, String expiryDate) {
        // Kiểm tra thông tin thẻ trong danh sách thẻ hợp lệ
        return VALID_CARDS.stream().anyMatch(card ->
                card[0].equals(cardNumber) &&
                        card[1].equals(cardName) &&
                        card[2].equals(expiryDate)
        );
    }
}