package com.example.my_shoppe.controller;

import com.example.my_shoppe.dao.CategoryRepository;
import com.example.my_shoppe.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {
    private CategoryRepository categoryRepository;
    @Autowired
    public ApiController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @GetMapping("/getAll")
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }
}
