package com.projects.atm.system;

import com.projects.atm.system.daoimpl.ATMService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ATMService atmService = new ATMService();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.nextLine();

        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        if (!atmService.validateAccount(accountNumber, pin)) {
            System.out.println("Invalid Account Number or PIN.");
            return;
        }

        System.out.println();

        while (true) {
            System.out.println("ATM Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Balance: " + atmService.checkBalance(accountNumber));
                    break;

                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    if (atmService.deposit(accountNumber, depositAmount))
                        System.out.println("Deposit successful.");
                    else
                        System.out.println("Deposit failed.");
                    break;

                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    if (atmService.withdraw(accountNumber, withdrawAmount))
                        System.out.println("Withdrawal successful.");
                    else
                        System.out.println("Insufficient balance.");
                    break;

                case 4:
                    System.out.println("Thank you for using our ATM!");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
