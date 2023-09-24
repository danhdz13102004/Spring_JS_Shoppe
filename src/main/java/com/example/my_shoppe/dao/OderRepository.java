package com.example.my_shoppe.dao;

import com.example.my_shoppe.entity.Oder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OderRepository extends JpaRepository<Oder,Long> {
   public Oder findByIdKhachHang(Long idKhachHang);

}
