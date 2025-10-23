import java.util.Scanner;

class PriorityDemoThread extends Thread {
    private int loops;

    public PriorityDemoThread(String name, int priority, int loops) {
        super(name);
        setPriority(priority);
        this.loops = loops;
    }

    @Override
    public void run() {
        for (int i = 0; i < loops; i++) {
            System.out.println(getName() + " with priority " + getPriority()
                + " — iteration " + i);
            try { Thread.sleep(100); } catch (InterruptedException e) {}
        }
    }
}

public class ThreadPriority {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("How many iterations for each thread? ");
        int numLoops = sc.nextInt();

        PriorityDemoThread low = new PriorityDemoThread(
            "LowPriorityThread", Thread.MIN_PRIORITY, numLoops);
        PriorityDemoThread high = new PriorityDemoThread(
            "HighPriorityThread", Thread.MAX_PRIORITY, numLoops);

        low.start();
        high.start();

        try {
            low.join();
            high.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Both threads finished.");
        sc.close();
    }
}
