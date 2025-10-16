import java.util.Random;

class NumberGenerator extends Thread {
    int num;
    Random rand = new Random();

    public void run() {
        try {
            while (true) {
                num = rand.nextInt(100); 
                System.out.println("\nGenerated Number: " + num);

                if (num % 2 == 0) {
                    new Square(num).start();
                } else {
                    new Cube(num).start();
                }

                Thread.sleep(1000); 
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}

class Square extends Thread {
    int num;

    Square(int n) {
        num = n;
    }

    public void run() {
        System.out.println("Square of " + num + " = " + (num * num));
    }
}

class Cube extends Thread {
    int num;

    Cube(int n) {
        num = n;
    }

    public void run() {
        System.out.println("Cube of " + num + " = " + (num * num * num));
    }
}

public class MultiThread {
    public static void main(String[] args) {
        NumberGenerator generator = new NumberGenerator();
        generator.start();
    }
}
