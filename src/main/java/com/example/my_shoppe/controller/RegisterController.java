package com.example.my_shoppe.controller;

import com.example.my_shoppe.dao.RoleRepository;
import com.example.my_shoppe.dao.UserRepository;
import com.example.my_shoppe.entity.Role;
import com.example.my_shoppe.entity.User;
import com.example.my_shoppe.service.UserService;
import com.example.my_shoppe.web.RegisterUser;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private UserService userService;
    private RoleRepository roleRepository;
    @Autowired
    public RegisterController(UserService userService,RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }
    @GetMapping("/formRegister")
    public String showRegisterForm(Model model){
        RegisterUser ru = new RegisterUser();
        model.addAttribute("registerUser", ru);
        return "register/form";
    }
    @InitBinder
    public void initBinder(WebDataBinder data){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        data.registerCustomEditor(String.class, stringTrimmerEditor);
    }
    @PostMapping("/process")
    public String process(@Valid @ModelAttribute("registerUser") RegisterUser registerUser,
                          BindingResult result,
                          Model model,
                          HttpSession session
    ){
        String username = registerUser.getUsername();

        // form validation
        if(result.hasErrors()){
            List<FieldError> errors = result.getFieldErrors();
//
//           model.addAttribute("listError",errors);
            for(FieldError err : errors) System.out.println(err.getDefaultMessage());
            return "register/form";

        }

        // kiểm tra user đã tồn tại?
        User userExisting =userService.findByUsername(username);
        if (userExisting!=null){
            model.addAttribute("registerUser", new RegisterUser());
            model.addAttribute("my_error", "Tài khoản đã tồn tại!");
            return "register/form";
        }

        // Nếu chưa ton tai thi luu
        BCryptPasswordEncoder bcrypt= new BCryptPasswordEncoder();
        User user = new User();
        user.setUsername(registerUser.getUsername());
        user.setPassword(bcrypt.encode(registerUser.getPassword()));
        user.setFirstName(registerUser.getFirstname());
        user.setLastName(registerUser.getLastname());
        user.setEmail(registerUser.getEmail());

        Role defaultRole = roleRepository.findByName("ROLE_USER");
        Collection<Role> roles = new ArrayList<>();
        roles.add(defaultRole);
        user.setRoles(roles);

        userService.save(user);

        // thông báo thành công
        session.setAttribute("myuser", user);

        return "register/confirm";
    }
}
