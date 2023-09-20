package com.example.my_shoppe.service;

import com.example.my_shoppe.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public User findByUsername(String user);

    public void save(User user);

}
