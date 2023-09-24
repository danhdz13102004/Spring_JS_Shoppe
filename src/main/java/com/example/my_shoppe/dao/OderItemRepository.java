package com.example.my_shoppe.dao;

import com.example.my_shoppe.entity.OderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OderItemRepository extends JpaRepository<OderItem,Long> {
   public OderItem findByOderid(Long id);
   public List<OderItem> findAllByOderid(Long oder_id);

   public OderItem findByCategoryid(Long id);

   public OderItem findByCategoryidAndOderid(Long id1,Long id2);

   public void deleteByCategoryidAndOderid(Long id1,Long id2);

}
