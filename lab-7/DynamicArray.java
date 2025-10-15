import java.io.*;
import java.util.*;

class InvalidArraySizeException extends Exception {
    public InvalidArraySizeException(String message) {
        super(message);
    }
}

public class DynamicArray {
    public static void main(String[] args) {
        Scanner sc = null;
        PrintWriter logWriter = null;

        try {
            
            logWriter = new PrintWriter(new FileWriter("log.txt", true), true);

            sc = new Scanner(System.in);

            
            int size = 0;
            System.out.print("Enter array size: ");
            try {
                size = sc.nextInt();
                log("INPUT: Array size entered = " + size, logWriter);

                if (size <= 0)
                    throw new InvalidArraySizeException("Array size must be greater than zero.");

                int[] arr = new int[size];
                log("OUTPUT: Array of size " + size + " created successfully.", logWriter);
                System.out.println("Array of size " + size + " created successfully.");

            } catch (InvalidArraySizeException e) {
                log("EXCEPTION: " + e.getMessage(), logWriter);
                System.out.println("Custom Exception: " + e.getMessage());
            } catch (InputMismatchException e) {
                log("EXCEPTION: Invalid input entered by user.", logWriter);
                System.out.println("Invalid input! Please enter an integer.");
            } catch (Exception e) {
                log("EXCEPTION: " + e, logWriter);
                System.out.println("Unexpected Error: " + e);
            } finally {
                log("Execution completed.", logWriter);
                System.out.println("Execution completed.");
            }

        } catch (IOException e) {
            System.err.println("Failed to initialize logger: " + e.getMessage());
        } finally {
            
            if (sc != null) sc.close();
            if (logWriter != null) logWriter.close();
        }
    }


    private static void log(String message, PrintWriter logWriter) {
        logWriter.println(message);
        logWriter.flush();
    }
}
