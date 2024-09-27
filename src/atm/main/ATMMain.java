package atm.main;

import java.util.Scanner;

import atm.model.User;
import atm.service.Bank;

public class ATMMain {
	public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();

        System.out.print("Enter PIN: ");
        String pin = scanner.next();

        User user = bank.authenticateUser(userId, pin);

        if (user != null) {
            System.out.println("Welcome, " + user.getName());
            boolean exit = false;

            while (!exit) {
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Transfer Funds");
                System.out.println("5. Exit");

                System.out.print("Choose an option: ");
                int option = scanner.nextInt();

                switch (option) {
                    case 1:
                        double balance = bank.checkBalance(userId);
                        System.out.println("Your balance is: " + balance);
                        break;
                    case 2:
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        bank.deposit(userId, depositAmount);
                        System.out.println("Deposit successful.");
                        break;
                    case 3:
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        bank.withdraw(userId, withdrawAmount);
                        System.out.println("Withdrawal successful.");
                        break;
                    case 4:
                        System.out.print("Enter recipient User ID: ");
                        int recipientId = scanner.nextInt();
                        System.out.print("Enter amount to transfer: ");
                        double transferAmount = scanner.nextDouble();
                        bank.transfer(userId, recipientId, transferAmount);
                        System.out.println("Transfer successful.");
                        break;
                    case 5:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        } else {
            System.out.println("Invalid User ID or PIN.");
        }

        scanner.close();
    }


}
