package com.example.my_shoppe.controller;

import com.example.my_shoppe.dao.CategoryRepository;
import com.example.my_shoppe.entity.Category;
import com.example.my_shoppe.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private CategoryRepository categoryRepository;

    public HomeController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("")
    public String toHome(Model model) {

        List<Category> list = categoryRepository.findAll();
        for(Category x: list) {
            System.out.println(x);
        }
        model.addAttribute("list",list);

        return "idx";
    }
    @GetMapping("/home")
    public String toHome1(Model model) {
        List<Category> list = categoryRepository.findAll();
        model.addAttribute("list",list);
        return "idx";
    }
}
