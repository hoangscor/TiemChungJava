package edu.uth.tiemchungjava.controller;



import edu.uth.tiemchungjava.models.Category;
import edu.uth.tiemchungjava.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller

@RequestMapping("/admin")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/categoryAdmin")
    public String category(Model model) {
        List<Category> list = this.categoryService.getAll();
        model.addAttribute("list", list);
        return "categoryAdmin";
    }
    @RequestMapping("/add-category")
    public String add() {
        return "categoryAdmin/add";
    }
}
