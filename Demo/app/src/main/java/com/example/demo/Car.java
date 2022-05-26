package com.example.demo;

public class Car {
    private int carid;
    private String regno;
    private String brand;
    private String model;
    private int price;
    private String available;


    public Car(int carid, String regno, String brand, String model, int price, String available) {
        this.carid = carid;
        this.regno = regno;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.available = available;
    }


    public int getCarid() {
        return carid;
    }

    public void setCarid(int carid) {
        this.carid = carid;
    }

    public String getRegno() {
        return regno;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }
}
