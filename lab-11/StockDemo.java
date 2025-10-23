import java.util.Scanner;

class Stock {
    private int count = 0;

    public synchronized void addStock(int amount) {
        count += amount;
        System.out.println(Thread.currentThread().getName()
            + " added " + amount + " | Stock = " + count);
        notify();
    }

    public synchronized void getStock(int amount) {
        while (count < amount) {
            try {
                System.out.println(Thread.currentThread().getName()
                    + " waiting for " + amount + " | Current Stock = " + count);
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        count -= amount;
        System.out.println(Thread.currentThread().getName()
            + " consumed " + amount + " | Stock = " + count);
    }
}

class Producer implements Runnable {
    private Stock stock;
    private int times;
    private int amount;

    public Producer(Stock s, int times, int amount) {
        this.stock = s;
        this.times = times;
        this.amount = amount;
    }

    public void run() {
        for (int i = 0; i < times; i++) {
            stock.addStock(amount);
            try { Thread.sleep(200); } catch (InterruptedException e) {}
        }
    }
}

class Consumer implements Runnable {
    private Stock stock;
    private int times;
    private int amount;

    public Consumer(Stock s, int times, int amount) {
        this.stock = s;
        this.times = times;
        this.amount = amount;
    }

    public void run() {
        for (int i = 0; i < times; i++) {
            stock.getStock(amount);
            try { Thread.sleep(300); } catch (InterruptedException e) {}
        }
    }
}

public class StockDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("How many times should producer add stock? ");
        int prodTimes = sc.nextInt();
        System.out.print("How many times should consumer get stock? ");
        int consTimes = sc.nextInt();

        Stock shared = new Stock();
        Producer p = new Producer(shared, prodTimes, 10);
        Consumer c = new Consumer(shared, consTimes, 10);

        Thread prodThread = new Thread(p, "ProducerThread");
        Thread consThread = new Thread(c, "ConsumerThread");

        prodThread.start();
        consThread.start();

        try {
            prodThread.join();
            consThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        sc.close();
        
    }
}
