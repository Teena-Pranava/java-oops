import java.io.FileWriter;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Scanner;

class MathOperations {

    public int sum(int a, int b) {
        System.out.println("sum(int, int) called");
        return a + b;
    }

    public double sum(double a, double b, double c) {
        System.out.println("sum(double, double, double) called");
        return a + b + c;
    }

    public int sum(int... numbers) {
        System.out.println("sum(int...) called");
        int total = 0;
        for (int n : numbers) total += n;
        return total;
    }
}

public class MathOperationsLog {

    public static void main(String[] args) throws Exception {

        // Setup log file writer
        FileWriter fw = new FileWriter("log.txt", true); // append mode
        PrintWriter logWriter = new PrintWriter(fw, true);
        PrintStream originalOut = System.out;

        // Redirect output to both console and log file
        PrintStream logOut = new PrintStream(new OutputStream() {
            public void write(int b) {
                originalOut.write(b);
                logWriter.write(b);
            }
        });
        System.setOut(logOut);

        Scanner sc = new Scanner(System.in);
        MathOperations m = new MathOperations();

        System.out.print("Enter two integers: ");
        int a = sc.nextInt();
        int b = sc.nextInt();
        logWriter.println("INPUT: a = " + a + ", b = " + b);

        int result1 = m.sum(a, b);
        System.out.println("Result (two integers): " + result1);
        logWriter.println("OUTPUT: sum(int,int) = " + result1);

        System.out.print("\nEnter three decimal numbers: ");
        double x = sc.nextDouble();
        double y = sc.nextDouble();
        double z = sc.nextDouble();
        logWriter.println("INPUT: x = " + x + ", y = " + y + ", z = " + z);

        double result2 = m.sum(x, y, z);
        System.out.println("Result (three doubles): " + result2);
        logWriter.println("OUTPUT: sum(double,double,double) = " + result2);

        System.out.print("\nEnter number of integers for varargs sum: ");
        int count = sc.nextInt();
        int[] nums = new int[count];
        for (int i = 0; i < count; i++) {
            System.out.print("Enter number " + (i + 1) + ": ");
            nums[i] = sc.nextInt();
        }
        logWriter.println("INPUT (varargs): " + java.util.Arrays.toString(nums));

        int result3 = m.sum(nums);
        System.out.println("Result (varargs sum): " + result3);
        logWriter.println("OUTPUT: sum(int...) = " + result3);

        sc.close();
        logWriter.close();
    }
}
