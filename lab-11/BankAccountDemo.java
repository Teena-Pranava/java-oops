import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initial) {
        this.balance = initial;
    }

    public synchronized void deposit(double amount) {
        balance += amount;
        System.out.println(Thread.currentThread().getName()
            + " deposited " + amount + " | New balance: " + balance);
        notifyAll();
    }

    public synchronized void withdraw(double amount) {
        while (balance < amount) {
            try {
                System.out.println(Thread.currentThread().getName()
                    + " waiting to withdraw " + amount
                    + " | Current balance: " + balance);
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        balance -= amount;
        System.out.println(Thread.currentThread().getName()
            + " withdrew " + amount + " | New balance: " + balance);
    }

  
    public synchronized double getBalance() {
        return balance;
    }
}

public class BankAccountDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("How many deposits do you want to run? ");
        int numDeposits = sc.nextInt();
        System.out.print("How many withdrawals do you want to run? ");
        int numWithdrawals = sc.nextInt();

        BankAccount acct = new BankAccount(1000);

        Thread depositor = new Thread(() -> {
            for (int i = 0; i < numDeposits; i++) {
                acct.deposit(200);
                try { Thread.sleep(100); } 
                catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            }
        }, "DepositorThread");

        Thread withdrawer = new Thread(() -> {
            for (int i = 0; i < numWithdrawals; i++) {
                acct.withdraw(300);
                try { Thread.sleep(150); } 
                catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            }
        }, "WithdrawerThread");

        depositor.start();
        withdrawer.start();

        try {
            depositor.join();
            withdrawer.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Final balance: " + acct.getBalance());
        sc.close();
    }
}
