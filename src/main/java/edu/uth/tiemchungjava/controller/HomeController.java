package edu.uth.tiemchungjava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.ui.Model;

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

    @GetMapping("/login")
    public String handleLogin() {
        return "custom_login";
    }

    @GetMapping("/quytrinh")
    public String quytrinh() {
        return "quytrinh.html";
    }


    @GetMapping("/lydatlichtiem")//goi den html
    public String lydatlichtiem() {return "lydatlichtiem";}


    @GetMapping("/index")
    public String index() {return "index"; // goi den html home
    }
//    @GetMapping("/admin/home")//goi den admin
//    public String admin() {
//        model.addAttribute("title", "Admin");
//        return "homeAdmin.html";
//    }

    @GetMapping("/admin")
    public String admin() {return "homeAdmin";}

    @GetMapping("/user/home")
    public String user() {return "index";}     // goi den user

    @GetMapping("/Vaccine")
    public String Vaccine() {return "Vaccine.html";}

}
