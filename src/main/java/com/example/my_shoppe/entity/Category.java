package com.example.my_shoppe.entity;

import jakarta.persistence.*;
import org.springframework.context.annotation.Configuration;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "oldprice")
    private Long oldprice;

    @Column(name = "newprice")
    private Long newprice;

    @Column(name = "image")
    private String image;

    private String oldpriceShow ="" ;
    private String newpriceShow  ="";

    public Category() {
    }

    public Category(Long id, String name, Long oldprice, Long newprice, String image) {
        this.id = id;
        this.name = name;
        this.oldprice = oldprice;
        this.newprice = newprice;
        this.image = image;
        toPriceString();
    }

    public Category(String name, Long oldprice, Long newprice, String image) {
        this.name = name;
        this.oldprice = oldprice;
        this.newprice = newprice;
        this.image = image;
        toPriceString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOldprice() {
        return oldprice;
    }

    public void setOldprice(Long oldprice) {
        this.oldprice = oldprice;
    }

    public Long getNewprice() {
        return newprice;
    }

    public void setNewprice(Long newprice) {
        this.newprice = newprice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public void toPriceString() {
        String tmp1 = oldprice.toString();
        String tmp2 = newprice.toString();
        int d1=0,d2=0;
        for(int i=tmp1.length()-1;i>=0;i--) {
            d1++;
            oldpriceShow = tmp1.charAt(i) + oldpriceShow;
            if(d1%3==0 && i>0) oldpriceShow = '.' + oldpriceShow;
        }
        for(int i=tmp2.length()-1;i>=0;i--) {
            d2++;
            newpriceShow = tmp2.charAt(i) + newpriceShow;
            if(d2%3==0 && i>0) newpriceShow = '.' + newpriceShow;
        }
        System.out.println(oldpriceShow);
        System.out.println(newpriceShow);
    }

    public String getOldpriceShow() {
        return oldpriceShow;
    }

    public void setOldpriceShow(String oldpriceShow) {
        this.oldpriceShow = oldpriceShow;
    }

    public String getNewpriceShow() {
        return newpriceShow;
    }

    public void setNewpriceShow(String newpriceShow) {
        this.newpriceShow = newpriceShow;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", oldprice='" + oldprice + '\'' +
                ", newprice='" + newprice + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
