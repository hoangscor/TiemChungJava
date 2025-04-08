package edu.uth.tiemchungjava.controller;

import edu.uth.tiemchungjava.models.MyUser;
import edu.uth.tiemchungjava.models.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

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
    @Autowired
    private MyUserRepository myUserRepository;

    @GetMapping("/admin")
    public String admin(Authentication authentication, Model model) {
        // Lấy username của người dùng từ Authentication
        String username = ((User) authentication.getPrincipal()).getUsername();

        // Lấy thông tin người dùng từ cơ sở dữ liệu
        List<MyUser> userList = myUserRepository.findAll();  // Lấy tất cả người dùng

        // Truyền thông tin vào model
        model.addAttribute("username", username); // Truyền username vào model
        model.addAttribute("users", userList);    // Truyền danh sách người dùng vào model

        return "homeAdmin"; // Trả về view homeAdmin
    }

//    @GetMapping("/admin")
//    public String admin() {return "homeAdmin";}
    @GetMapping("/categoryAdmin")
    public String category() {return "categoryAdmin";}

    @GetMapping("/user/home")
    public String user() {return "index";}     // goi den user

    @GetMapping("/Vaccine")
    public String Vaccine() {return "Vaccine.html";}
    @GetMapping("/order")
    public String order() {return "order.html";}

    @GetMapping("/logout")
    public String logout() {
        // Spring Security sẽ tự động xử lý logout
        return "redirect:/login"; // Redirect về trang login sau khi logout
    }

}
