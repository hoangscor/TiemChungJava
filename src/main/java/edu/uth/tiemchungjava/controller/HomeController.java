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

    @GetMapping("/login")
    public String login() {
        return "index";
    }
    @GetMapping("/quytrinh")
    public String quytrinh() {
        return "quytrinh.html";
    }


    @GetMapping("/lydatlichtiem")//goi den html
    public String lydatlichtiem() {return "lydatlichtiem.html";}

    @GetMapping("/indexx")
    public String indexx() {
        return "indexx"; // goi den html home
    }
    @GetMapping("/admin/home")//goi den admin
    public String admin() {return "homeAdmin";}

    @GetMapping("/user/home")
    public String user() {return "indexx";} // goi den user

    @GetMapping("/Vaccine")
    public String Vaccine() {return "Vaccine.html";}
}
