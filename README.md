# Transaction Tracker CLI

## Overview
A basic command line application that can record deposits and payments created, as well as view those records. 

## Features

- **Add Transactions:** Can input deposits or payments. Will automatically record the date and time those entries were created.

- **View Records:** Can view all the created entries, or entries in certain timeframes (current/previous month, current/previous year).

- **Search Vendors:** Can search for transactions associated with certain vendors.

## Example
![image](https://github.com/user-attachments/assets/7d32ca6e-fbf6-4303-bc52-b945c0ab43be)


Here's a piece of code!

    public static void main(String[] args) {
        System.out.println("Welcome to this transaction application.");
        homeScreen();
        System.out.println("Thank you for using our application.");
    }

## Requirements

- Java 11 or higher
- Maven (for dependency management)

## Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/BryanG2442/TransactionTracker.git
   cd transaction-tracker-cli
