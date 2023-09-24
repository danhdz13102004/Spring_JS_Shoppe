package com.example.my_shoppe.controller;

import com.example.my_shoppe.dao.CategoryRepository;
import com.example.my_shoppe.dao.OderItemRepository;
import com.example.my_shoppe.dao.OderRepository;
import com.example.my_shoppe.dao.UserRepository;
import com.example.my_shoppe.entity.Category;
import com.example.my_shoppe.entity.Oder;
import com.example.my_shoppe.entity.OderItem;
import com.example.my_shoppe.entity.User;
import com.example.my_shoppe.web.CartItemRender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ApiController {
    private CategoryRepository categoryRepository;

    private OderRepository oderRepository;
    private OderItemRepository oderItemRepository;

    private UserRepository userRepository;

    @Autowired
    public ApiController(CategoryRepository categoryRepository,OderRepository oderRepository,OderItemRepository oderItemRepository,UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.oderRepository = oderRepository;
        this.oderItemRepository = oderItemRepository;
        this.userRepository = userRepository;
    }
    @GetMapping("/getAll")
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }
    @GetMapping("/itemcart")
    public List<CartItemRender> getItemCart() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        User user = userRepository.findByUsername(username);
        if(user != null) {
            Oder oder = oderRepository.findByIdKhachHang(user.getId());
            if(oder != null) {
                List<CartItemRender> list = new ArrayList<>();
                List<OderItem> oderItemList = oderItemRepository.findAllByOderid(oder.getId());
                for(OderItem item : oderItemList) {
                    Optional<Category> categoryOptional = categoryRepository.findById(item.getCategoryid());
                    if(categoryOptional.isPresent()) {
                        Category category = categoryOptional.get();
                        CartItemRender cartItemRender = new CartItemRender(category,item);
                        list.add(cartItemRender);
                        System.out.println(cartItemRender);
                    }
                }
                return list;
            }
        }
        return null;
    }
}
