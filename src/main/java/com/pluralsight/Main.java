package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    final static String FILEPATH = "src/transaction.csv";
    public static void main(String[] args) {
        System.out.println("Welcome to this transaction application.");
        homeScreen();
        System.out.println("Thank you for using our application.");



    }

    //The default screen the program goes through.
    public static void homeScreen() {
        Scanner scanner = new Scanner(System.in);

        String choice;
        do {
            System.out.println("\nPlease choose an option:\n" +
                    "To make a deposit, input D\n" +
                    "To make a payment, input P\n" +
                    "To view your ledger, input L\n" +
                    "to exit the program, input X");
            choice = scanner.nextLine();
            switch (choice){
                case "D":
                case "d":
                    makeDeposit(scanner);
                    break;
                case "P":
                case "p":
                    makePayment(scanner);
                    break;
                case "L":
                case "l":
                    ledgerMenu(scanner);
                    break;
                case "X":
                case "x":
                    break;
                default:
                    System.out.println("\nInvalid input. Please try a valid option");
        }} while (!(choice.equalsIgnoreCase("X")));
    scanner.close();


    }

    public static void makeDeposit(Scanner scanner) {
        System.out.print("Please input the deposit source: ");
        String source = scanner.nextLine();
        System.out.print("Please input the buyer: ");
        String buyer = scanner.nextLine();
        System.out.print("Please input the deposit amount: ");
        double price = Math.abs(getDouble(scanner));
        Transactions transactions = new Transactions();
        String toFile = String.format("%S|%S|%S|%S|%.2f\n",transactions.getDate(), transactions.getTime(), source, buyer, price);
        writeToFile(toFile);
        scanner.nextLine();

    }
    public static double getDouble(Scanner scanner){
        double outputDouble = 0;
        do { try {
            outputDouble = scanner.nextDouble();
        } catch (InputMismatchException e) {
            System.out.println("Please input a valid value");
        } } while (outputDouble == 0);
        return outputDouble;
    }

    public static void makePayment(Scanner scanner) {
        System.out.print("Please input the purchased item: ");
        String source = scanner.nextLine();
        System.out.print("Please input the seller: ");
        String seller = scanner.nextLine();
        System.out.print("Please input the payment amount: ");
        double price = -Math.abs(getDouble(scanner));
        Transactions transactions = new Transactions();
        String toFile = String.format("%S|%S|%S|%S|%.2f\n",transactions.getDate(), transactions.getTime(), source, seller, price);
        writeToFile(toFile);
        scanner.nextLine();

    }
    //Ledger menu
    public static void ledgerMenu(Scanner scanner) {
        ArrayList<Transactions> transactionsArrayList = new ArrayList<>(getTransactions());
        String choice;
        do {
            System.out.println("Welcome to the Ledger menu. Please choose an option." +
                    "\nTo view all entries, please enter A" +
                    "\nTo view all deposit, please enter D" +
                    "\nTo view all payments, please enter P" +
                    "\nTo run reports, please enter R" +
                    "\nTo go back to the home screen, please enter H");
            choice = scanner.nextLine();
            switch (choice){
                case "A":
                case "a":
                    showTransactions(transactionsArrayList);
                    break;
                case "D":
                case "d":
                    showDeposits(transactionsArrayList);
                    break;
                case "P":
                case "p":
                    showPayments(transactionsArrayList);
                    break;
                case "R":
                case "r":
                    reportsMenu(scanner, transactionsArrayList);
                    break;
                case "H":
                case "h":
                    break;
                default:
                    System.out.println("Invalid option, please input a valid choice.");
            }

        }   while (!choice.equalsIgnoreCase("H"));
    }


    //Reports menu
    public static void reportsMenu(Scanner scanner, ArrayList<Transactions> transactions){
        String choice;
        do {
            System.out.println("Welcome to the reports menu. Please choose an option." +
                    "\nPress 1 for all entries for the current month" +
                    "\nPress 2 for all entries in the previous month" +
                    "\nPress 3 for all entries in the current year" +
                    "\nPress 4 for all entries in the previous year" +
                    "\nPress 5 to search entries by vendor" +
                    "\nPress 0 to return to the reports menu");
            choice = scanner.nextLine();
            switch (choice){
                case "1":
                    showCurrentMonthTransactions(transactions);
                    break;
                case "2":
                    showPreviousMonthTransactions(transactions);
                    break;
                case "3":
                    showCurrentYearTransactions(transactions);
                    break;
                case "4":
                    showPreviousYearTransactions(transactions);
                    break;
                case "5":
                    searchVendor(scanner, transactions);
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Please input a valid option");
                    break;
            }
        } while (!choice.equalsIgnoreCase("0"));

    }


    //Gets the current month and the month of a given transaction and displays it if they match
    public static void  showCurrentMonthTransactions (ArrayList<Transactions> transactionsArrayList){

        for (int i = transactionsArrayList.size(); i > 0; i--) {

            if (LocalDate.now().getMonth() == LocalDate.parse(transactionsArrayList.get(i-1).getCreatedDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")).getMonth()){
                    transactionsArrayList.get(i-1).printInfo();

            }
        }
        System.out.println();
    }
    //Gets the previous month and the month of a given transaction and displays it if they match
    public static void  showPreviousMonthTransactions (ArrayList<Transactions> transactionsArrayList){

        for (int i = transactionsArrayList.size(); i > 0; i--) {
            if (LocalDate.now().minusMonths(1).getMonth() == LocalDate.parse(transactionsArrayList.get(i-1).getCreatedDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")).getMonth()){
                transactionsArrayList.get(i-1).printInfo();
            }
        }
        System.out.println();
    }

    //Gets the current year and the year of a given transaction and displays it if they match
    public static void  showCurrentYearTransactions (ArrayList<Transactions> transactionsArrayList){

        for (int i = transactionsArrayList.size(); i > 0; i--) {

            if (LocalDate.now().getYear() == LocalDate.parse(transactionsArrayList.get(i-1).getCreatedDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")).getYear()){
                transactionsArrayList.get(i-1).printInfo();

            }
        }
        System.out.println();
    }
    //Gets the previous year and the year of a given transaction and displays it if they match
    public static void  showPreviousYearTransactions (ArrayList<Transactions> transactionsArrayList){

        for (int i = transactionsArrayList.size(); i > 0; i--) {

            if (LocalDate.now().minusYears(1).getYear() == LocalDate.parse(transactionsArrayList.get(i-1).getCreatedDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")).getYear()){
                transactionsArrayList.get(i-1).printInfo();

            }
        }
        System.out.println();
    }


    public static void searchVendor (Scanner scanner, ArrayList<Transactions> transactionsArrayList) {
        System.out.print("Please input the vendor you wish to search for: ");
        String searchTerm = scanner.nextLine();
        searchTerm = searchTerm.toUpperCase();
        for (int i = transactionsArrayList.size(); i > 0; i--){
            if (transactionsArrayList.get(i-1).getAmount() < 0){
                if (transactionsArrayList.get(i-1).getVendor().contains(searchTerm)){
                    transactionsArrayList.get(i-1).printInfo();
                }
            }
        }
    }


    //Gets the current list of transactions and puts it into an array
    public static ArrayList<Transactions> getTransactions(){
        ArrayList<Transactions> transactionsList = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(FILEPATH);
            BufferedReader readFile = new BufferedReader(fileReader);

            String input;
            while ((input = readFile.readLine()) != null){
                String[] objectVariables = input.split("\\|");
                transactionsList.add(new Transactions(objectVariables[0], objectVariables[1], objectVariables[2], objectVariables[3], Float.parseFloat(objectVariables[4])));

            }
            readFile.close();
        } catch (IOException e) {
            System.out.println("Error reading file has occurred");
            e.printStackTrace();
        }

        return transactionsList;
    }


    //A basic loop to show all transactions
    public static void showTransactions (ArrayList<Transactions> transactionList) {
        for (int i = transactionList.size(); i > 0; i--) {
            transactionList.get(i-1).printInfo();
        }
        System.out.println();
    }


    //Slightly edited loop to show all positive amount values (deposits)
    public static void showDeposits (ArrayList<Transactions> transactionList) {
        for (int i = transactionList.size(); i > 0; i--) {
            if (transactionList.get(i-1).getAmount() > 0) {
                transactionList.get(i - 1).printInfo();
            }
        }
        System.out.println();
    }



    //Slightly edited loop to show all negative amount values (payments)
    public static void showPayments (ArrayList<Transactions> transactionList) {
        for (int i = transactionList.size(); i > 0; i--) {
            if (transactionList.get(i-1).getAmount() < 0) {
                transactionList.get(i - 1).printInfo();
            }        }
        System.out.println();
    }






    //Write to file method (used for deposits and payments)
    public static void writeToFile(String _input){
        try {
            FileWriter fileWriter = new FileWriter(FILEPATH, true);
            BufferedWriter outputFile = new BufferedWriter(fileWriter);
            outputFile.write(_input);
            outputFile.close();
        } catch (IOException e) {
            System.out.println("Error writing to file");
        }
}














}