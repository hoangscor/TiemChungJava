package edu.uth.tiemchungjava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class HomeController {
    @GetMapping("/gioithieu")
    public String gioiThieu() {
        return "gioithieu";
    }
    @GetMapping("/doingu")
    public String doiNgu() {
        return "doingu";
    }
    @GetMapping("/lichtiem")
    public String lichTiem() {
        return "lichtiem";
    }
    @GetMapping("/register")
    public String register() {
        return "register.html";
    }
}
