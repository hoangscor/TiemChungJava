package edu.uth.tiemchungjava.controller;

import edu.uth.tiemchungjava.dto.VaccineDTO;
import edu.uth.tiemchungjava.models.MyUser;
import edu.uth.tiemchungjava.models.MyUserRepository;
import edu.uth.tiemchungjava.models.VaccinationBooking;
import edu.uth.tiemchungjava.models.Vaccine;
import edu.uth.tiemchungjava.repository.VaccineRepository;
import edu.uth.tiemchungjava.service.VaccinationBookingService;
import edu.uth.tiemchungjava.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller

public class HomeController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    // Hiển thị form tạo người dùng mới (tại /admin/user/create)
    @GetMapping("/admin/user/create")
    public String showCreateUserForm(Model model) {
        model.addAttribute("user", new MyUser());
        return "createUser"; // Đây là tên file Thymeleaf (createUser.html) sẽ tạo bên dưới
    }

    // Xử lý tạo người dùng mới
    @PostMapping("/admin/user/create")
    public String createUser(@ModelAttribute("user") MyUser user) {
        // Mã hóa mật khẩu
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Nếu không set role thì gán mặc định là USER
        if (user.getRole() == null || user.getRole().trim().isEmpty()) {
            user.setRole("USER");
        }
        myUserRepository.save(user);
        return "redirect:/admin"; // Quay về trang admin sau khi tạo xong
    }
    // Hiển thị form chỉnh sửa người dùng (tại /admin/user/edit/{id})
    @GetMapping("/admin/user/edit/{id}")
    public String showEditUserForm(@PathVariable("id") Long id, Model model) {
        MyUser user = myUserRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        return "editUser"; // Sẽ render file editUser.html bên dưới
    }

    // Xử lý cập nhật người dùng
    @PostMapping("/admin/user/edit/{id}")
    public String updateUser(@PathVariable("id") Long id, @ModelAttribute("user") MyUser user) {
        user.setId(id); // Đảm bảo id đúng
        // Cập nhật mật khẩu (mã hóa lại mật khẩu mới)
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        myUserRepository.save(user);
        return "redirect:/admin"; // Sau khi update quay về trang admin
    }

    // Xóa người dùng theo id (tại /admin/user/delete/{id})
    @GetMapping("/admin/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        myUserRepository.deleteById(id);
        return "redirect:/admin";
    }



    //    ----------------------------------------------------------------------------------
    @Autowired
    private VaccinationBookingService bookingService;

    @GetMapping("/lichsu")
    public String getOrderHistory(
            @RequestParam(value = "page", defaultValue = "1") int page,
            Model model) {
        int pageSize = 4; // Mỗi trang hiển thị 8 nội dung
        Page<VaccinationBooking> orderPage = bookingService.getPaginatedBookings(page, pageSize);

        // Đẩy dữ liệu của trang hiện tại (content trong Page) vào model
        model.addAttribute("orderHistory", orderPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", orderPage.getTotalPages());
        model.addAttribute("totalItems", orderPage.getTotalElements());
        model.addAttribute("selectedMenu", "history"); // Set selected menu for history page

        return "lichsu";
    }



    @GetMapping("/gioithieu")
    public String gioiThieu(Model model) {
        model.addAttribute("selectedMenu", "gioithieu");
        return "gioithieu";
    }


    @GetMapping("/doingu")
    public String doiNgu(Model model) {
        model.addAttribute("selectedMenu", "doingu");
        return "doingu";
    }



    @GetMapping("/lichtiem")
    public String lichTiem(Model model) {
        model.addAttribute("selectedMenu", "lichtiem");
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


    @GetMapping("/giaodiendatlichtiem")//goi den html
    public String giaodiendatlichtiem() {return "giaodiendatlichtiem";}

    @GetMapping("/chatbox")//goi den html
    public String chatBox() {return "chatbox";}


    @Autowired
    private VaccineRepository vaccineRepository;
    @GetMapping("/index")
    public String index(Model model) {
        List<Vaccine> vaccineList = vaccineRepository.findAll();

        model.addAttribute("vaccines", vaccineList);
        model.addAttribute("selectedMenu", "home");
        return "index";
    }
    @Autowired
    private MyUserRepository myUserRepository;

    @GetMapping("/admin")
    public String admin(Authentication authentication,
                        @RequestParam(value = "page", defaultValue = "1") int page,
                        Model model) {
        // Lấy username của người dùng từ Authentication
        String username = ((User) authentication.getPrincipal()).getUsername();

        // Define the page size (number of items per page)
        int pageSize = 4;
        // Get paginated users
        Page<MyUser> userPage = myUserRepository.findAll(PageRequest.of(page - 1, pageSize));

        // Lấy thông tin người dùng từ cơ sở dữ liệu
        List<MyUser> userList = myUserRepository.findAll();  // Lấy tất cả người dùng

        // Truyền thông tin vào model
        model.addAttribute("username", username); // Truyền username vào model
        model.addAttribute("users", userList);    // Truyền danh sách người dùng vào model
        model.addAttribute("users", userPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", userPage.getTotalPages());
        model.addAttribute("totalItems", userPage.getTotalElements());
        model.addAttribute("selectedMenu", "dashboard"); // Set selected menu for dashboard page

        return "homeAdmin"; // Trả về view homeAdmin
    }
    @Autowired
    private VaccineService service;

    @GetMapping("/donhang")
    public String getDonHang(@RequestParam(value = "page", defaultValue = "1") int page, Model model)
    {
        int pageSize = 4;  // Set the number of items per page
        Page<VaccineDTO> vaccinePage = service.getPaginatedVaccines(page, pageSize); // Pagination method

        model.addAttribute("vaccines", vaccinePage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", vaccinePage.getTotalPages());
        model.addAttribute("totalItems", vaccinePage.getTotalElements());
        model.addAttribute("selectedMenu", "products"); // Set selected menu for products page

        List<VaccineDTO> vaccines = service.getAllVaccines(); // Lấy dữ liệu từ VaccineService
        model.addAttribute("vaccines", vaccines); // Truyền vào model
        return "donhang"; // Trả về trang donhang.html
    }

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
        return "redirect:/index"; // Redirect về trang login sau khi logout
    }
    @GetMapping("/")
    public String home() {
        return "redirect:/index"; // Chuyển hướng tới /index
    }
}
