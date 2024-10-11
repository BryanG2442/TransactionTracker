package com.pluralsight;

import java.util.Scanner;

public class Main {
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
                case "D": break;
                case "P": break;
                case "L": break;
                case "X": break;
                default:
                    System.out.println("\nInvalid input. Please try a valid option");
        }} while (!(choice.equalsIgnoreCase("X")));



    }











}