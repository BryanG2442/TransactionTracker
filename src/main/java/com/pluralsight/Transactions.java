package com.pluralsight;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Transactions {
    private LocalDateTime currentTime;
    private DateTimeFormatter dateFormat;

    private String createdDate;
    private String createdTime;
    private String description;
    private String vendor;
    private double amount;


    public Transactions(String _createdDate, String _createdTime, String _description, String _vendor, double _amount){
        this.description = _description;
        this.vendor = _vendor;
        this.amount = _amount;
        this.createdTime = _createdTime;
        this.createdDate = _createdDate;
    }
    public Transactions(){}

    public String getDate(){
        dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        currentTime = LocalDateTime.now();
        return  currentTime.format(dateFormat);
    }

    public String getTime(){
        dateFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
        currentTime = LocalDateTime.now();
        return currentTime.format(dateFormat);
    }

    public void printInfo() {
        System.out.printf("%S|%S|%S|%S|%.2f\n", createdDate, createdTime, description, vendor, amount);
    }

    public double getAmount() {
        return amount;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getVendor() {
        return vendor;
    }
}
