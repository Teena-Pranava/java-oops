import java.io.*;
import java.util.Scanner;

public class FileOperationsMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n====== File Operations Menu ======");
            System.out.println("1. Check if file is found at a particular location");
            System.out.println("2. Get last modification date and time of a file");
            System.out.println("3. Rename an existing file");
            System.out.println("4. Create directory/folder in particular drive");
            System.out.println("5. Check whether a file can be read or not");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    checkFileExists(sc);
                    break;
                case 2:
                    getLastModified(sc);
                    break;
                case 3:
                    renameFile(sc);
                    break;
                case 4:
                    createDirectory(sc);
                    break;
                case 5:
                    checkReadable(sc);
                    break;
                case 6:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 6);
        sc.close();
    }


    static void checkFileExists(Scanner sc) {
        System.out.print("Enter full file path: ");
        String path = sc.nextLine();
        File file = new File(path);
        try {
            if (file.exists()) {
                System.out.println("File found at: " + file.getAbsolutePath());
            } else {
                throw new FileNotFoundException("File not found at given location!");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

   
    static void getLastModified(Scanner sc) {
        System.out.print("Enter full file path: ");
        String path = sc.nextLine();
        File file = new File(path);
        if (file.exists()) {
            long lastModified = file.lastModified();
            System.out.println("Last modified: " + new java.util.Date(lastModified));
        } else {
            System.out.println("File not found!");
        }
    }

  
    static void renameFile(Scanner sc) {
        System.out.print("Enter current file path: ");
        String oldPath = sc.nextLine();
        System.out.print("Enter new file path (including new name): ");
        String newPath = sc.nextLine();

        File oldFile = new File(oldPath);
        File newFile = new File(newPath);

        if (oldFile.exists()) {
            boolean success = oldFile.renameTo(newFile);
            if (success) {
                System.out.println("File renamed successfully!");
            } else {
                System.out.println("Failed to rename file!");
            }
        } else {
            System.out.println("File not found!");
        }
    }


    static void createDirectory(Scanner sc) {
        System.out.print("Enter directory path to create: ");
        String path = sc.nextLine();
        File dir = new File(path);
        if (!dir.exists()) {
            if (dir.mkdirs()) {
                System.out.println("Directory created successfully at: " + dir.getAbsolutePath());
            } else {
                System.out.println(" Failed to create directory!");
            }
        } else {
            System.out.println("Directory already exists!");
        }
    }

    
    static void checkReadable(Scanner sc) {
        System.out.print("Enter file path: ");
        String path = sc.nextLine();
        File file = new File(path);
        if (file.exists()) {
            if (file.canRead()) {
                System.out.println("File can be read.");
            } else {
                System.out.println(" File cannot be read.");
            }
        } else {
            System.out.println("File not found!");
        }
    }
}
