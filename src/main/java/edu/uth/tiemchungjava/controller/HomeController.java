package edu.uth.tiemchungjava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class HomeController {
    @GetMapping("/gioithieu")
    public String gioithieu() {
        return "gioithieu.html";
    }
    @GetMapping("/doingu")
    public String doingu() {
        return "doingu.html";
    }
}
