package com.example.my_shoppe.entity;

import java.util.HashMap;
import java.util.Map;

public class UserCart  {
    private Map<Category,Integer> listItems;

    public UserCart() {
        this.listItems = new HashMap<>();
    }
    public void addItem(Category category,int quantity) {
        if(listItems.containsKey(category)) {
            int quantityCurrent = listItems.get(category);
            listItems.put(category,quantityCurrent + quantity);
        }
        else {
            listItems.put(category,quantity);
        }
    }
    // Xóa sản phẩm khỏi giỏ hàng
    public void removeItem(Category category) {
        listItems.remove(category);
    }

    // Lấy danh sách sản phẩm trong giỏ hàng
    public Map<Category, Integer> getCartItems() {
        return listItems;
    }

    // Tính tổng giá trị của giỏ hàng
    public double getTotalPrice() {
        double totalPrice = 0.0;
        for (Map.Entry<Category, Integer> entry : listItems.entrySet()) {
            Category product = entry.getKey();
            int quantity = entry.getValue();
            totalPrice += product.getNewprice() * quantity * 1000;
        }
        return totalPrice;
    }


}
