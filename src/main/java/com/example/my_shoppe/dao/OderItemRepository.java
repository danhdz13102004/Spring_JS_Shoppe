package com.example.my_shoppe.dao;

import com.example.my_shoppe.entity.OderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OderItemRepository extends JpaRepository<OderItem,Long> {
   public OderItem findByOderId(Long id);
   public List<OderItem> findAllByOderId(Long oder_id);

   public OderItem findByCategoryId(Long id);

   public OderItem findByCategoryIdAndOderId(Long id1,Long id2);
}
