package edu.uth.tiemchungjava.service;

import edu.uth.tiemchungjava.models.VaccinationBooking;
import edu.uth.tiemchungjava.repository.VaccinationBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VaccinationBookingService {

    @Autowired
    private VaccinationBookingRepository bookingRepository;

    // Hàm lấy danh sách đơn hàng (lịch sử đặt lịch)
    public List<VaccinationBooking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public VaccinationBooking createBooking(VaccinationBooking booking) {
        // Đặt trạng thái mặc định là PENDING (chờ thanh toán)
        booking.setStatus("PENDING");
        booking.setPaymentStatus("UNPAID");
        return bookingRepository.save(booking);
    }

    public Optional<VaccinationBooking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    public VaccinationBooking updateBooking(VaccinationBooking booking) {
        return bookingRepository.save(booking);
    }

    public void cancelBooking(Long id) {
        Optional<VaccinationBooking> bookingOpt = bookingRepository.findById(id);
        if (bookingOpt.isPresent()) {
            VaccinationBooking booking = bookingOpt.get();
            booking.setStatus("CANCELLED");
            bookingRepository.save(booking);
        }
    }
}