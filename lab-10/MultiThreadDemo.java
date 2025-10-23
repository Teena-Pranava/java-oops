public class MultiThreadDemo {
   
    static class WeatherThread extends Thread {
        public WeatherThread(String name) {
            super(name);
        }
        @Override
        public void run() {
           
            System.out.println(Thread.currentThread().getName() 
                               + ": Today is hot, humid and sunny.");
        }
    }

    
    static class PrintRunnable implements Runnable {
        private final String message;
        private final long intervalMillis;

        public PrintRunnable(String message, long intervalMillis) {
            this.message = message;
            this.intervalMillis = intervalMillis;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println(Thread.currentThread().getName() 
                                       + ": " + message);
                    Thread.sleep(intervalMillis);
                }
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName()  + " interrupted.");
            }
        }
    }

    public static void main(String[] args) {
       
        WeatherThread t1 = new WeatherThread("WeatherThread-1");
        WeatherThread t2 = new WeatherThread("WeatherThread-2");
        t1.start();
        t2.start();

   
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
           
        }

        
        Thread hiThread = new Thread(new PrintRunnable("HI", 300), "HI-Thread");
        Thread aiThread = new Thread(new PrintRunnable("AI", 300), "AI-Thread");
        hiThread.start();
        aiThread.start();

       
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            
        }
        hiThread.interrupt();
        aiThread.interrupt();

        System.out.println("Main thread exiting.");
    }
}
