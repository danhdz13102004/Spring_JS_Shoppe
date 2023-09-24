package com.example.my_shoppe.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "oder_item")
public class OderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "oderid")
    private Long oderid;

    @Column(name = "categoryid")
    private Long categoryid;

    @Column(name = "price")
    private Long price;

    @Column(name = "quantity")
    private Integer quantity;

    public OderItem() {
    }

    public OderItem(Long id, Long oderid, Long categoryid, Long price, Integer quantity) {
        this.id = id;
        this.oderid = oderid;
        this.categoryid = categoryid;
        this.price = price;
        this.quantity = quantity;
    }

    public OderItem(Long oderid, Long categoryid, Long price, Integer quantity) {
        this.oderid = oderid;
        this.categoryid = categoryid;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOderid() {
        return oderid;
    }

    public void setOderid(Long oderid) {
        this.oderid = oderid;
    }

    public Long getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Long categoryid) {
        this.categoryid = categoryid;
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
