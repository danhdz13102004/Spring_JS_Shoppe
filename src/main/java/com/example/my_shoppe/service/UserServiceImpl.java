package com.example.my_shoppe.service;

import com.example.my_shoppe.dao.CategoryRepository;
import com.example.my_shoppe.dao.RoleRepository;
import com.example.my_shoppe.dao.UserRepository;
import com.example.my_shoppe.entity.Role;
import com.example.my_shoppe.entity.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    private CategoryRepository categoryRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository,RoleRepository roleRepository,CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public User findByUsername(String user) {
        return userRepository.findByUsername(user);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
    @PostConstruct
    public void insert() {
        User user = new User();
        user.setUsername("danh");
        user.setPassword("$2a$12$9QN5T2jH2LhcaDh1VtHwy./LrVCYoymtDNXu5ZVp5duf3hsGx/RPq");
        user.setEnabled(true);
        Collection<Role> list = new ArrayList<>();
        Role role = new Role();
        role.setName("ROLE_USER");
        list.add(role);
        user.setRoles(list);
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),rolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> rolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
