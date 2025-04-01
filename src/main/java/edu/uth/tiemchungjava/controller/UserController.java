//package edu.uth.tiemchungjava.controller;
//import edu.uth.tiemchungjava.dto.LoginRequest;
//import edu.uth.tiemchungjava.dto.LoginResponse;
//import edu.uth.tiemchungjava.models.User;
//import edu.uth.tiemchungjava.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Optional;
//
//@Controller
//@RestController
//@RequestMapping("/api/users")
//@CrossOrigin(origins = "*")
//public class UserController {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @PostMapping("/login")
//    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
//        Optional<User> userOpt = userRepository.findByUsername(loginRequest.getUsername());
//
//        if (userOpt.isPresent()) {
//            User user = userOpt.get();
//
//            // Trong ứng dụng thực tế, bạn nên mã hóa mật khẩu và so sánh an toàn
//            if (user.getPassword().equals(loginRequest.getPassword())) {
//                return ResponseEntity.ok(new LoginResponse(
//                        user.getId(),
//                        user.getUsername(),
//                        user.getEmail(),
//                        true,
//                        "Đăng nhập thành công"
//                ));
//            }
//        }
//
//        return ResponseEntity.ok(new LoginResponse(false, "Tên đăng nhập hoặc mật khẩu không đúng"));
//    }
//
//
//    @PostMapping("/register")
//    public ResponseEntity<LoginResponse> register(@RequestBody User user) {
//        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
//            return ResponseEntity.ok(new LoginResponse(false, "Tên đăng nhập đã tồn tại"));
//        }
//
//        User savedUser = userRepository.save(user);
//        return ResponseEntity.ok(new LoginResponse(
//                savedUser.getId(),
//                savedUser.getUsername(),
//                savedUser.getEmail(),
//                true,
//                "Đăng ký thành công"
//        ));
//    }
//}
