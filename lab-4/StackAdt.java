import java.io.*;
import java.util.Scanner;


public class StackAdt {
    public static void main(String[] args) throws Exception {
        
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

        
        Scanner scanner;
        if (args.length > 0) {
            File inputFile = new File(args[0]);
            if (inputFile.exists()) {
                scanner = new Scanner(inputFile);
                System.out.println("Reading input from file: " + args[0]);
            } else {
                System.out.println("File not found. Switching to console input.");
                scanner = new Scanner(System.in);
            }
        } else {
            scanner = new Scanner(System.in);
        }

        
        System.out.print("Enter initial stack size: ");
        int initialSize = scanner.nextInt();
        System.out.println("INPUT initialSize: " + initialSize);
        scanner.nextLine();

        
        System.out.println("\n--- Select Stack Type ---");
        System.out.println("1. String\n2. Integer");
        System.out.print("Choice: ");
        int typeChoice = scanner.nextInt();
        System.out.println("INPUT StackTypeChoice: " + typeChoice);
        scanner.nextLine();

        
        System.out.println("\n--- Stack Menu ---");
        System.out.println("1. Push  2. Pop  3. Display  4. Exit");

        if (typeChoice == 1) {
            runStringStack(scanner, initialSize);
        } else {
            runIntegerStack(scanner, initialSize);
        }

        scanner.close();
        logWriter.close();
        System.out.println("Program exiting...");
    }

   
    private static void runStringStack(Scanner scanner, int initialSize) {
        MyStack<String> stack = new StackArray<>(initialSize);

        while (true) {
            System.out.print("\nChoice: ");
            int choice = scanner.nextInt();
            System.out.println("INPUT MenuChoice: " + choice);
            scanner.nextLine();

            switch (choice) {
                case 1: 
                    System.out.print("Enter value to push: ");
                    String val = scanner.nextLine();
                    System.out.println("INPUT PushValue: " + val);
                    try {
                        stack.push(val);
                        System.out.println(val + " pushed to stack.");
                    } catch (StackOverflowException e) {
                        System.out.println("Exception: " + e.getMessage());
                    }
                    break;
                case 2: 
                    try {
                        String popped = stack.pop();
                        System.out.println("OUTPUT Popped: " + popped);
                    } catch (StackUnderflowException e) {
                        System.out.println("Exception: " + e.getMessage());
                    }
                    break;
                case 3: 
                    stack.display();
                    break;
                case 4: 
                    return;
                default:
                    System.out.println("Enter a valid choice [1-4]");
            }
        }
    }


    private static void runIntegerStack(Scanner scanner, int initialSize) {
        MyStack<Integer> stack = new StackArray<>(initialSize);

        while (true) {
            System.out.print("\nChoice: ");
            int choice = scanner.nextInt();
            System.out.println("INPUT MenuChoice: " + choice);
            scanner.nextLine();

            switch (choice) {
                case 1: 
                    System.out.print("Enter integer value to push: ");
                    int num = scanner.nextInt();
                    System.out.println("INPUT PushValue: " + num);
                    scanner.nextLine();
                    try {
                        stack.push(num);
                        System.out.println(num + " pushed to stack.");
                    } catch (StackOverflowException e) {
                        System.out.println("Exception: " + e.getMessage());
                    }
                    break;
                case 2: 
                    try {
                        Integer popped = stack.pop();
                        System.out.println("OUTPUT Popped: " + popped);
                    } catch (StackUnderflowException e) {
                        System.out.println("Exception: " + e.getMessage());
                    }
                    break;
                case 3: 
                    stack.display();
                    break;
                case 4: 
                    return;
                default:
                    System.out.println("Enter a valid choice [1-4]");
            }
        }
    }
}


interface MyStack<T> {
    void push(T item) throws StackOverflowException;
    T pop() throws StackUnderflowException;
    void display();
    boolean isEmpty();
    boolean isFull();
}


class StackOverflowException extends Exception {
    public StackOverflowException(String message) {
        super(message);
    }
}

class StackUnderflowException extends Exception {
    public StackUnderflowException(String message) {
        super(message);
    }
}


class StackArray<T> implements MyStack<T> {
    private static final int MAX_CAPACITY = 100;
    private Object[] elements;
    private int top;
    private int capacity;

    public StackArray(int initialCapacity) {
        if (initialCapacity <= 0 || initialCapacity > MAX_CAPACITY) {
            throw new IllegalArgumentException("Capacity must be between 1 and " + MAX_CAPACITY);
        }
        this.capacity = initialCapacity;
        this.elements = new Object[capacity];
        this.top = -1;
    }

    @Override
    public void push(T item) throws StackOverflowException {
        if (size() == capacity) {
            if (capacity == MAX_CAPACITY) {
                throw new StackOverflowException("Stack reached maximum capacity (" + MAX_CAPACITY + ")");
            }
            int newCapacity = Math.min(capacity * 2, MAX_CAPACITY);
            resize(newCapacity);
        }
        elements[++top] = item;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T pop() throws StackUnderflowException {
        if (isEmpty()) {
            throw new StackUnderflowException("Cannot pop from empty stack.");
        }
        T value = (T) elements[top];
        elements[top--] = null; // remove reference
        return value;
    }

    @Override
    public void display() {
        System.out.print("Stack elements (top to bottom): [ ");
        for (int i = top; i >= 0; i--) {
            System.out.print(elements[i]);
            if (i != 0) System.out.print(", ");
        }
        System.out.println(" ]");
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public boolean isFull() {
        return size() == capacity;
    }

    public int size() {
        return top + 1;
    }

    private void resize(int newCapacity) {
        Object[] newArr = new Object[newCapacity];
        System.arraycopy(elements, 0, newArr, 0, size());
        elements = newArr;
        capacity = newCapacity;
        System.out.println("Stack resized to capacity: " + capacity);
    }
}
