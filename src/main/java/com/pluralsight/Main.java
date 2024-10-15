package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
                    makeDeposit(scanner);
                    break;
                case "P": break;
                case "L": break;
                case "X": break;
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
        String toFile = String.format("%S|%S|%S|%S|%.2f",transactions.getDate(), transactions.getTime(), source, buyer, price);
        writeToFile(toFile);
        scanner.nextLine();

    }



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