package com.example.my_shoppe.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "oder_item")
public class OderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "oder_id")
    private Long oderId;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "price")
    private Long price;

    @Column(name = "quantity")
    private Integer quantity;

    public OderItem() {
    }

    public OderItem(Long id, Long oderId, Long categoryId, Long price, Integer quantity) {
        this.id = id;
        this.oderId = oderId;
        this.categoryId = categoryId;
        this.price = price;
        this.quantity = quantity;
    }

    public OderItem(Long oderId, Long categoryId, Long price, Integer quantity) {
        this.oderId = oderId;
        this.categoryId = categoryId;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOderId() {
        return oderId;
    }

    public void setOderId(Long oderId) {
        this.oderId = oderId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
