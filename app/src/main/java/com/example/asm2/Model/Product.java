package com.example.asm2.Model;

public class Product {
    private int idProd;
    private String name;
    private String price;
    private String SoLuong;

    public Product(int idProd, String name, String price, String soLuong) {
        this.idProd = idProd;
        this.name = name;
        this.price = price;
        SoLuong = soLuong;
    }

    public Product(String name, String price, String soLuong) {
        this.name = name;
        this.price = price;
        SoLuong = soLuong;
    }

    public Product() {
    }

    public int getIdProd() {
        return idProd;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(String soLuong) {
        SoLuong = soLuong;
    }
}