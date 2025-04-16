package edu.uth.tiemchungjava.controller;

import edu.uth.tiemchungjava.models.Feedback;
import edu.uth.tiemchungjava.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/submit")
    public ResponseEntity<?> submitFeedback(@RequestBody Map<String, Object> feedbackData) {
        try {
            // Lấy dữ liệu từ request
            String name = (String) feedbackData.get("name");
            Integer rating = Integer.parseInt(feedbackData.get("rating").toString());
            String comment = (String) feedbackData.get("comment");

            // Tạo đối tượng Feedback
            Feedback feedback = new Feedback();
            feedback.setName(name);
            feedback.setRating(rating);
            feedback.setComment(comment);

            // Lưu đánh giá vào database
            feedbackService.saveFeedback(feedback);

            // Trả về kết quả thành công
            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Cảm ơn bạn đã đánh giá!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Xử lý lỗi
            Map<String, String> response = new HashMap<>();
            response.put("error", "Đã xảy ra lỗi khi gửi đánh giá: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}