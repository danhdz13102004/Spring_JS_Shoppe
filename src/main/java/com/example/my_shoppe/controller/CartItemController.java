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
import com.example.my_shoppe.web.CategoryDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cart")
public class CartItemController {
    private OderRepository oderRepository;
    private OderItemRepository oderItemRepository;

    private CategoryRepository categoryRepository;
    private UserRepository userRepository;

    @Autowired
    public CartItemController(OderRepository oderRepository, OderItemRepository oderItemRepository,UserRepository userRepository,CategoryRepository categoryRepository) {
        this.oderRepository = oderRepository;
        this.oderItemRepository = oderItemRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/add")
    public void addItemToCart(@RequestParam("id") Long id, @RequestParam("quantity") Integer quantity) {
        System.out.println("Tới đây rùi nha");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        User userCurrent = userRepository.findByUsername(username);
        Oder oder = oderRepository.findByIdKhachHang(userCurrent.getId());
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        Category category = null;
        if (categoryOptional.isPresent()) {
            category = categoryOptional.get();
        }
            if (oder == null) {
                List<Oder> list = oderRepository.findAll();
                oder = new Oder();
                oder.setId(Long.valueOf(list.size() + 1));
                oder.setIdKhachHang(userCurrent.getId());
                oder.setTotal(category.getNewprice() * quantity);
            } else {
                Long totalCurrent = oder.getTotal();
                oder.setTotal(totalCurrent + category.getNewprice() * quantity);
            }
            OderItem oderItem = oderItemRepository.findByCategoryidAndOderid(id,oder.getId());
            if(oderItem == null) {
                if(quantity > 0) {
                    oderItem = new OderItem(oder.getId(), category.getId(), category.getNewprice(), quantity);
                    System.out.println("Khong co");
                    oderItemRepository.save(oderItem);
                }
            }
            else {
                System.out.println("Co roi");
                int newsl = quantity + oderItem.getQuantity();
                if(!(quantity == -1 && oderItem.getQuantity() ==1)) {
                    if (newsl <= 0) {
                        oderItemRepository.deleteById(oderItem.getId());
                    } else {
                        oderItem.setQuantity(newsl);
                        oderItemRepository.saveAndFlush(oderItem);
                    }
                }
            }
        oderRepository.saveAndFlush(oder);
//        return oder;
        }
        @GetMapping("/deleteitem")
        public void deleteItem(@RequestParam("id") Long id) {
            System.out.println("tới đây để xóa");
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            User user = userRepository.findByUsername(username);
            Oder oder = oderRepository.findByIdKhachHang(user.getId());
            System.out.println(id + " " + oder.getId());
            OderItem oderItem = oderItemRepository.findByCategoryidAndOderid(id,oder.getId());
            System.out.println(oderItem.toString());
            oderItemRepository.deleteById(oderItem.getId());
//            oderItemRepository.deleteById(Long.valueOf(10));
    }
    @GetMapping("/detail")
    public String toDetailCart(Model model) {
        System.out.println("Có tới hay khong ?");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        User user = userRepository.findByUsername(username);
        Oder oder = oderRepository.findByIdKhachHang(user.getId());
        List<OderItem> list = oderItemRepository.findAllByOderid(oder.getId());
        List<CategoryDetail> listReturn = new ArrayList<>();
        for(OderItem item : list) {
            Optional<Category> categoryOptional = categoryRepository.findById(item.getCategoryid());
            Category category = null;
            if(categoryOptional.isPresent()) {
                category = categoryOptional.get();
            }
            CategoryDetail cartItemRender = new CategoryDetail(category,item);
            listReturn.add(cartItemRender);
//            System.out.println(cartItemRender.toString());
        }
        long totalReturn = 0;
        for(CategoryDetail item : listReturn) {
            totalReturn += item.getTotal();
        }
        model.addAttribute("total",listReturn.size());
       model.addAttribute("list",listReturn);
        UriComponents uriComponents = ServletUriComponentsBuilder.fromCurrentContextPath().build();
        String baseUrl = uriComponents.toUriString();
        model.addAttribute("url",baseUrl);
        model.addAttribute("mytotal",totalReturn);
        return "cart/list";
    }


    }
