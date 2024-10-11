package com.pluralsight;

import java.time.LocalDateTime;

public class Transactions {
    private LocalDateTime currentTime;
    private String description;
    private String vendor;
    private double amount;


    public Transactions(LocalDateTime _currentTime, String _description, String _vendor, double _amount){
        currentTime = _currentTime;
        description = _description;
        vendor = _vendor;
        amount = _amount;
    }
    public Transactions(){}






}
