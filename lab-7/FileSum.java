import java.io.*;
import java.util.*;

public class FileSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter logWriter = null;

        try {
            
            logWriter = new PrintWriter(new FileWriter("log.txt", true), true);
            logWriter.println("\n===== Program Execution Started =====");

            
            File file = new File("numbers.txt");
            Scanner fileScanner = null;
            int fileSum = 0;

            try {
                fileScanner = new Scanner(file);
                logWriter.println("Reading from file: " + file.getName());

                while (fileScanner.hasNext()) {
                    String token = fileScanner.next();
                    try {
                        int num = Integer.parseInt(token);
                        fileSum += num;
                    } catch (NumberFormatException e) {
                        logWriter.println("Invalid number found in file: " + token);
                    }
                }

                System.out.println("Sum of numbers in file = " + fileSum);
                logWriter.println("OUTPUT: Sum of numbers in file = " + fileSum);

            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + e.getMessage());
                logWriter.println("EXCEPTION: File not found — " + e.getMessage());
            } finally {
                if (fileScanner != null) {
                    fileScanner.close();
                    logWriter.println("File closed safely.");
                }
            }

            try {
                System.out.print("\nEnter first number: ");
                int a = sc.nextInt();
                logWriter.println("INPUT a: " + a);

                System.out.print("Enter second number: ");
                int b = sc.nextInt();
                logWriter.println("INPUT b: " + b);

                int sum = a + b;
                int diff = a - b;

                System.out.println("Sum = " + sum);
                System.out.println("Difference = " + diff);

                logWriter.println("OUTPUT Sum: " + sum);
                logWriter.println("OUTPUT Difference: " + diff);

            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter integers only.");
                logWriter.println("EXCEPTION: Invalid input type — " + e.getMessage());
            }

        } catch (IOException e) {
            System.out.println("Error creating or writing to log file: " + e.getMessage());
        } finally {
            if (logWriter != null) {
                logWriter.println("===== Program Execution Completed =====");
                logWriter.close();
            }
            sc.close();
            System.out.println("Execution completed. Log saved to log.txt");
        }
    }
}
