package com.pluralsight;

import java.io.*;
import java.util.ArrayList;
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
        double price = scanner.nextDouble();
        Transactions transactions = new Transactions();
        String toFile = String.format("%S|%S|%S|%S|%.2f\n",transactions.getDate(), transactions.getTime(), source, buyer, price);
        writeToFile(toFile);
        scanner.nextLine();

    }

    public static void makePayment(Scanner scanner) {
        System.out.print("Please input the purchased item: ");
        String source = scanner.nextLine();
        System.out.print("Please input the seller: ");
        String seller = scanner.nextLine();
        System.out.print("Please input the payment amount: ");
        double price = -scanner.nextDouble();
        Transactions transactions = new Transactions();
        String toFile = String.format("%S|%S|%S|%S|%.2f\n",transactions.getDate(), transactions.getTime(), source, seller, price);
        writeToFile(toFile);
        scanner.nextLine();

    }
    //Ledger menu
    public static void ledgerMenu(Scanner scanner) {
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
                    showTransactions(getTransactions());
                    break;
                case "D":
                case "d":
                    showDeposits(getTransactions());
                    break;
                case "P":
                case "p":
                    showPayments(getTransactions());
                case "R":
                case "r":
                    break;
                case "H":
                case "h":
                    break;
                default:
                    System.out.println("Invalid option, please input a valid choice.");
            }

        }   while (!choice.equalsIgnoreCase("H"));
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

    public static void showDeposits (ArrayList<Transactions> transactionList) {
        for (int i = transactionList.size(); i > 0; i--) {
            if (transactionList.get(i-1).getAmount() > 0) {
                transactionList.get(i - 1).printInfo();
            }
        }
        System.out.println();
    }

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