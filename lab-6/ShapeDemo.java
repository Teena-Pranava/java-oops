import java.io.*;
import java.util.*;


interface Colorable {
    String colorDescription();
}

abstract class Shape implements Colorable {
    protected double dim1, dim2;

    public Shape(double dim1, double dim2) {
        if (dim1 <= 0 || dim2 <= 0)
            throw new IllegalArgumentException("Dimensions must be positive numbers.");
        this.dim1 = dim1;
        this.dim2 = dim2;
    }

    public abstract double area();

    public final void displayInfo(String shapeName) {
        System.out.println("Shape: " + shapeName);
        System.out.println("Area: " + area());
        System.out.println("Color: " + colorDescription());
        System.out.println("--------------------------------");
    }
}


class Rectangle extends Shape {
    private String color;

    public Rectangle(double length, double width, String color) {
        super(length, width);
        this.color = color;
    }

    @Override
    public double area() {
        return dim1 * dim2;
    }

    @Override
    public String colorDescription() {
        return "Rectangle color is " + color;
    }
}

class Triangle extends Shape {
    private String color;

    public Triangle(double base, double height, String color) {
        super(base, height);
        this.color = color;
    }

    @Override
    public double area() {
        return 0.5 * dim1 * dim2;
    }

    @Override
    public String colorDescription() {
        return "Triangle color is " + color;
    }
}


class Circle extends Shape {
    private String color;

    public Circle(double radius, String color) {
        super(radius, radius);
        this.color = color;
    }

    @Override
    public double area() {
        return Math.PI * dim1 * dim1;
    }

    @Override
    public String colorDescription() {
        return "Circle color is " + color;
    }
}


public class ShapeDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

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

            String[] colors = {"Red", "Blue", "Green", "Yellow", "Purple"};
            Shape[] shapes = new Shape[3];

            System.out.println("----- SHAPE INPUT -----");

            try {
                System.out.print("Enter length and width for Rectangle: ");
                double l = sc.nextDouble(), w = sc.nextDouble();
                logWriter.println("INPUT Rectangle: " + l + ", " + w);
                shapes[0] = new Rectangle(l, w, colors[rand.nextInt(colors.length)]);

                System.out.print("Enter base and height for Triangle: ");
                double b = sc.nextDouble(), h = sc.nextDouble();
                logWriter.println("INPUT Triangle: " + b + ", " + h);
                shapes[1] = new Triangle(b, h, colors[rand.nextInt(colors.length)]);

                System.out.print("Enter radius for Circle: ");
                double r = sc.nextDouble();
                logWriter.println("INPUT Circle radius: " + r);
                shapes[2] = new Circle(r, colors[rand.nextInt(colors.length)]);

                System.out.println("\n----- SHAPE OUTPUT -----");
                for (Shape s : shapes) {
                    s.displayInfo(s.getClass().getSimpleName());
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter numeric values.");
                logWriter.println("ERROR: Invalid input! Non-numeric entered.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                logWriter.println("ERROR: " + e.getMessage());
            } finally {
                sc.close();
                logWriter.close();
            }

        } catch (IOException e) {
            System.out.println("Error setting up log file: " + e.getMessage());
        }
    }
}
