import java.io.*;
import java.util.*;

class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

class BankAccount {
    private double balance;
    private PrintWriter logWriter;

    public BankAccount(double balance, PrintWriter logWriter) {
        this.balance = balance;
        this.logWriter = logWriter;
    }

    public void transfer(double amount) throws InsufficientBalanceException {
        try {
            log("Attempting transfer of: " + amount);
            if (amount > balance)
                throw new InsufficientBalanceException("Transfer failed — Insufficient balance.");

            balance -= amount;
            log("Transfer successful. Remaining balance: " + balance);
            System.out.println("Transfer successful. Remaining balance: " + balance);

            return;

        } finally {
            try (FileWriter fw = new FileWriter("log.txt", true)) {
                fw.write("Transaction attempt: Amount=" + amount + ", FinalBalance=" + balance + "\n");
                fw.flush();
                log("Transaction logged .");
                System.out.println("Transaction logged .");
            } catch (IOException e) {
                log("Error writing transaction log: " + e.getMessage());
            }
        }
    }

    private void log(String msg) {
        logWriter.println("[BankAccount] " + msg);
        logWriter.flush();
    }
}

public class TransactionTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter logWriter = null;

        try {
            
            logWriter = new PrintWriter(new FileWriter("log.txt", true), true);
            logWriter.println("\n===== Program Execution Started =====");

        
            double initialBalance = 0;
            while (true) {
                System.out.print("Enter initial account balance: ");
                String input = sc.nextLine();
                try {
                    initialBalance = Double.parseDouble(input);
                    logWriter.println("[INPUT] Initial balance = " + initialBalance);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter a numeric value.");
                    logWriter.println("Invalid initial balance input: " + input);
                }
            }

            BankAccount account = new BankAccount(initialBalance, logWriter);

            
            int numTransactions = 0;
            while (true) {
                System.out.print("Enter number of transactions: ");
                String input = sc.nextLine();
                try {
                    numTransactions = Integer.parseInt(input);
                    logWriter.println(" Number of transactions = " + numTransactions);
                    if (numTransactions <= 0) {
                        System.out.println("Enter a positive integer.");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter an integer.");
                    logWriter.println("Invalid number of transactions input: " + input);
                }
            }

            for (int i = 1; i <= numTransactions; i++) {
                double amount = 0;
                while (true) {
                    System.out.print("Enter amount for transaction " + i + ": ");
                    String amtInput = sc.nextLine();
                    try {
                        amount = Double.parseDouble(amtInput);
                        logWriter.println(" Transaction " + i + " amount = " + amount);
                        if (amount <= 0) {
                            System.out.println("Enter a positive amount.");
                            continue;
                        }
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input! Please enter a numeric value.");
                        logWriter.println(" Invalid transaction input: " + amtInput);
                    }
                }

                
                try {
                    account.transfer(amount);
                } catch (InsufficientBalanceException e) {
                    System.out.println("Caught Exception: " + e.getMessage());
                    logWriter.println("EXCEPTION" + e.getMessage());
                }
            }

        } catch (IOException e) {
            System.out.println("Error setting up log file: " + e.getMessage());
        } finally {
            if (logWriter != null) {
                logWriter.println("===== Program Execution Completed =====");
                logWriter.close();
            }
            sc.close();
            System.out.println("\nExecution completed. Logs saved to log.txt and transaction_log.txt");
        }
    }
}
