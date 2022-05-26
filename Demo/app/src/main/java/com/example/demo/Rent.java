package com.example.demo;

public class Rent {
    private String rentid;
    private String carreg;
    private String rent_cusid;
    private String rentaldate;
    private String returndate;
    private String fees;
    private String paid;

    public Rent(String rentid, String carreg, String rent_cusid, String rentaldate, String returndate, String fees, String paid) {
        this.rentid = rentid;
        this.carreg = carreg;
        this.rent_cusid = rent_cusid;
        this.rentaldate = rentaldate;
        this.returndate = returndate;
        this.fees = fees;
        this.paid = paid;
    }

    public String getRentid() {
        return rentid;
    }

    public void setRentid(String rentid) {
        this.rentid = rentid;
    }

    public String getCarreg() {
        return carreg;
    }

    public void setCarreg(String carreg) {
        this.carreg = carreg;
    }

    public String getRent_cusid() {
        return rent_cusid;
    }

    public void setRent_cusid(String rent_cusid) {
        this.rent_cusid = rent_cusid;
    }

    public String getRentaldate() {
        return rentaldate;
    }

    public void setRentaldate(String rentaldate) {
        this.rentaldate = rentaldate;
    }

    public String getReturndate() {
        return returndate;
    }

    public void setReturndate(String returndate) {
        this.returndate = returndate;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }
}
