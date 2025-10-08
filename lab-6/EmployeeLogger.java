import java.io.*;
import java.util.*;


class InvalidSalaryException extends Exception {
    public InvalidSalaryException(String message) {
        super(message);
    }
}


class Employee {
    protected String name;
    protected double baseSalary;

    public Employee(String name, double baseSalary) throws InvalidSalaryException {
        if (baseSalary <= 0)
            throw new InvalidSalaryException("Base salary must be a positive value.");
        this.name = name;
        this.baseSalary = baseSalary;
    }

    public double calculateSalary() {
        return baseSalary;
    }
}


class Manager extends Employee {
    private double bonus;

    public Manager(String name, double baseSalary, double bonus) throws InvalidSalaryException {
        super(name, baseSalary);
        if (bonus < 0)
            throw new InvalidSalaryException("Bonus cannot be negative.");
        this.bonus = bonus;
    }

    @Override
    public double calculateSalary() {
        double base = super.calculateSalary();
        return base + bonus;
    }

    public void displaySalaryDetails() {
        System.out.println("Manager Name: " + name);
        System.out.println("Base Salary: " + baseSalary);
        System.out.println("Bonus: " + bonus);
        System.out.println("Total Salary: " + calculateSalary());
        System.out.println("--------------------------------");
    }
}


public class EmployeeLogger {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
          
            FileWriter fw = new FileWriter("log.txt", true); 
            PrintWriter logWriter = new PrintWriter(fw, true);
            PrintStream originalOut = System.out;

          
            PrintStream logOut = new PrintStream(new OutputStream() {
                public void write(int b) {
                    originalOut.write(b);
                    logWriter.write(b);
                }
            });
            System.setOut(logOut);

        
            System.out.println("----- EMPLOYEE SALARY SYSTEM -----");
            System.out.print("Enter employee name: ");
            String name = sc.nextLine();
            logWriter.println("INPUT Name: " + name);

            System.out.print("Enter base salary: ");
            double baseSalary = sc.nextDouble();
            logWriter.println("INPUT Base Salary: " + baseSalary);

            System.out.print("Enter bonus (for manager): ");
            double bonus = sc.nextDouble();
            logWriter.println("INPUT Bonus: " + bonus);

           
            Manager manager = new Manager(name, baseSalary, bonus);

        
            System.out.println("\n----- SALARY DETAILS -----");
            manager.displaySalaryDetails();

            logWriter.println("OUTPUT Total Salary: " + manager.calculateSalary());
            logWriter.println("--------------------------------\n");

            logWriter.close();
        } 
        catch (InvalidSalaryException e) {
            System.out.println("Error: " + e.getMessage());
        } 
        catch (InputMismatchException e) {
            System.out.println("Error: Please enter valid numeric values.");
        } 
        catch (IOException e) {
            System.out.println("Error setting up log file: " + e.getMessage());
        } 
        finally {
            sc.close();
        }
    }
}
