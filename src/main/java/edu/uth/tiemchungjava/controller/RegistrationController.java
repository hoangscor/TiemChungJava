package edu.uth.tiemchungjava.controller;

import edu.uth.tiemchungjava.models.MyUser;
import edu.uth.tiemchungjava.models.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RegistrationController {
    @Autowired
    private MyUserRepository myUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("register/user")
    public MyUser createUser(@RequestBody MyUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Gán role mặc định là USER nếu chưa có
        if (user.getRole() == null) {
            user.setRole("USER");
        }


        return myUserRepository.save(user);
    }
}
