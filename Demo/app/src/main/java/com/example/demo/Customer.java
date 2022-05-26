package com.example.demo;

public class Customer {

    private int cusid;
    private String cusname;
    private  String cusadd;
    private  String phone;

    public Customer(int cusid, String cusname, String cusadd, String phone) {
        this.cusid = cusid;
        this.cusname = cusname;
        this.cusadd = cusadd;
        this.phone = phone;
    }


    public int getCusid() {
        return cusid;
    }

    public void setCusid(int cusid) {
        this.cusid = cusid;
    }

    public String getCusname() {
        return cusname;
    }

    public void setCusname(String cusname) {
        this.cusname = cusname;
    }

    public String getCusadd() {
        return cusadd;
    }

    public void setCusadd(String cusadd) {
        this.cusadd = cusadd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
