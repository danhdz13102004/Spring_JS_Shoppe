package com.example.my_shoppe.web;

import com.example.my_shoppe.entity.Category;
import com.example.my_shoppe.entity.OderItem;
import org.springframework.beans.factory.annotation.Autowired;

public class CartItemRender {
    private Category category;
    private OderItem oderItem;

    public CartItemRender(Category category, OderItem oderItem) {
        this.category = category;
        this.oderItem = oderItem;
    }

    public CartItemRender() {
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public OderItem getOderItem() {
        return oderItem;
    }

    public void setOderItem(OderItem oderItem) {
        this.oderItem = oderItem;
    }

    @Override
    public String toString() {
        return "CartItemRender{" +
                "category=" + category +
                ", oderItem=" + oderItem +
                '}';
    }
}
