package com.example.my_shoppe.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "oder")
public class Oder {
    @Id
    @Column( name = "id")
    private Long id;
    @Column(name = "idkhachhang")
    private Long idKhachHang;

    @Column(name = "tenkhachhang")
    private String tenKhachHang;

    @Column(name = "sodienthoai")
    private String soDienThoai;

    @Column(name = "diachi")
    private String diaChi;

    @Column(name = "total")
    private Long total;

    public Oder() {
    }

    public Oder(Long id, Long idKhachHang, String tenKhachHang, String soDienThoai, String diaChi, Long total) {
        this.id = id;
        this.idKhachHang = idKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
        this.total = total;
    }

    public Oder(Long idKhachHang, String tenKhachHang, String soDienThoai, String diaChi, Long total) {
        this.idKhachHang = idKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long idKhachHang() {
        return idKhachHang;
    }

    public void idKhachHang(Long id_user) {
        this.idKhachHang = id_user;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(Long idKhachHang) {
        this.idKhachHang = idKhachHang;
    }
}
