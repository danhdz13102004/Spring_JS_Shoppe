package com.example.my_shoppe.dao;

import com.example.my_shoppe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public User findByUsername(String username);
    public User findByEmail(String email);
}
