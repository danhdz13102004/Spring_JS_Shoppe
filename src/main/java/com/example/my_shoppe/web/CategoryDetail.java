package com.example.my_shoppe.web;

import com.example.my_shoppe.entity.Category;
import com.example.my_shoppe.entity.Oder;
import com.example.my_shoppe.entity.OderItem;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class CategoryDetail {
    private Long idsp;

    private String name;
    private String img;
    private Long price;

    private Long quantity;
    private Long total;

    public CategoryDetail() {
    }
    public CategoryDetail(Category category,OderItem oderItem) {
        this.idsp = category.getId();
        this.name = category.getName();
        this.img = category.getImage();
        this.price = category.getNewprice();
        this.quantity = Long.valueOf(oderItem.getQuantity());
        this.total = this.quantity * this.price;
    }

    public Long getIdsp() {
        return idsp;
    }

    public void setIdsp(Long idsp) {
        this.idsp = idsp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "CategoryDetail{" +
                "name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", total=" + total +
                '}';
    }
}
