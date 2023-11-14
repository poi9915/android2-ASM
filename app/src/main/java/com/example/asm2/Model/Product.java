package com.example.asm2.Model;

public class Product{
    private int idProd;
    private String price;
    private String SoLuong;


    public Product() {
    }

    public Product(int idProd, String price, String soLuong) {
        this.idProd = idProd;
        this.price = price;
        SoLuong = soLuong;
    }

    public int getIdProd() {
        return idProd;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
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
