import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

// Custom unchecked exception
class UnderAgeException extends RuntimeException {
    public UnderAgeException(String message) {
        super(message);
    }
}

// Custom checked exception
class OverAgeException extends Exception {
    public OverAgeException(String message) {
        super(message);
    }
}

public class VotingValidation {
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // helper that prints to console AND writes to the log file (if available)
    private static void log(PrintWriter logger, String message) {
        String line = String.format("[%s] %s", SDF.format(new Date()), message);
        System.out.println(line);
        if (logger != null) {
            logger.println(line);
            logger.flush();
        }
    }

    // validation method: throws OverAgeException (checked) or UnderAgeException (unchecked)
    static void checkVotingEligibility(int age) throws OverAgeException {
        if (age < 18)
            throw new UnderAgeException("Age below 18 — Not eligible to vote.");
        if (age > 120)
            throw new OverAgeException("Age too high — Invalid age value.");
        // otherwise eligible (no exception)
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PrintWriter logWriter = null;

        try {
            // Open log file in append mode
            logWriter = new PrintWriter(new FileWriter("log.txt", true), true);
            log(logWriter, "Program started");

            System.out.print("Enter number of people to check: ");
            String countLine = scanner.nextLine().trim();
            int n;
            try {
                n = Integer.parseInt(countLine);
            } catch (NumberFormatException e) {
                log(logWriter, "Invalid input for number of people: '" + countLine + "'");
                System.out.println("Please enter a valid integer for number of people.");
                return;
            }
            log(logWriter, "INPUT: Number of people = " + n);

            for (int i = 1; i <= n; i++) {
                System.out.print("Enter age of person " + i + ": ");
                String ageLine = scanner.nextLine().trim();

                int age;
                try {
                    age = Integer.parseInt(ageLine);
                } catch (NumberFormatException e) {
                    log(logWriter, "INVALID INPUT: Age for person " + i + " -> '" + ageLine + "'");
                    System.out.println("Invalid age input. Please enter an integer.");
                    i--; // repeat this person
                    continue;
                }

                log(logWriter, "INPUT: Age of person " + i + " = " + age);

                try {
                    checkVotingEligibility(age);
                    log(logWriter, "OUTPUT: Age " + age + " — Eligible to vote!");
                } catch (UnderAgeException e) {
                    log(logWriter, "EXCEPTION (Unchecked): " + e.getMessage());
                } catch (OverAgeException e) {
                    log(logWriter, "EXCEPTION (Checked): " + e.getMessage());
                } catch (Exception e) {
                    log(logWriter, "EXCEPTION (Other): " + e.toString());
                }
            }

        } catch (IOException e) {
            System.err.println("Error opening log file: " + e.getMessage());
        } finally {
            log(logWriter, "Validation process completed.");
            if (logWriter != null) logWriter.close();
            scanner.close();
        }
    }
}
