package com.pluralsight;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Transactions {
    private static LocalDateTime currentTime;
    private static DateTimeFormatter dateFormat;
    private String description;
    private String vendor;
    private double amount;


    public Transactions(String _description, String _vendor, double _amount){
        description = _description;
        vendor = _vendor;
        amount = _amount;
    }
    public Transactions(){}

    public String getDate(){
        dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        currentTime = LocalDateTime.now();
        return  currentTime.format(dateFormat);
    }

    public String getTime(){
        dateFormat = DateTimeFormatter.ofPattern("HH-mm-ss");
        currentTime = LocalDateTime.now();
        return currentTime.format(dateFormat);
    }



}
