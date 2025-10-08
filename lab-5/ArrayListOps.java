import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Comparator;

public class ArrayListOps {
    private ArrayList<String> list = new ArrayList<>();

    // Append
    public void append(String str) {
        list.add(str);
    }

    // Insert at index
    public void insert(int index, String str) {
        if (index >= 0 && index <= list.size()) {
            list.add(index, str);
        } else {
            System.out.println("Invalid index!");
        }
    }

    // Search (case-sensitive)
    public boolean search(String str) {
        return list.contains(str);
    }

    // Search case-insensitive using regex
    public ArrayList<String> regexSearch(String regex) {
        ArrayList<String> result = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        for (String s : list) {
            Matcher matcher = pattern.matcher(s);
            if (matcher.find()) {
                result.add(s);
            }
        }
        return result;
    }

    // List strings starting with a letter
    public ArrayList<String> startsWith(char letter) {
        ArrayList<String> result = new ArrayList<>();
        for (String s : list) {
            if (s.toLowerCase().startsWith(Character.toString(letter).toLowerCase())) {
                result.add(s);
            }
        }
        Collections.sort(result);
        return result;
    }

    // Sort ascending
    public void sortAsc() {
        Collections.sort(list);
    }

    // Sort descending
    public void sortDesc() {
        Collections.sort(list, Collections.reverseOrder());
    }

    // Remove duplicates
    public void removeDuplicates() {
        list = new ArrayList<>(new LinkedHashSet<>(list));
    }

    // Partial match search (substring)
    public ArrayList<String> partialSearch(String substring) {
        ArrayList<String> result = new ArrayList<>();
        for (String s : list) {
            if (s.toLowerCase().contains(substring.toLowerCase())) {
                result.add(s);
            }
        }
        return result;
    }

    // Sort by length (Bonus)
    public void sortByLength() {
        list.sort(Comparator.comparingInt(String::length));
    }

    // Display list
    public void display() {
        System.out.println(list);
    }

    // Menu-driven program
    public static void main(String[] args) {
        ArrayListOps ops = new ArrayListOps();
        Scanner sc = new Scanner(System.in);

        int choice;
        do {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Append");
            System.out.println("2. Insert at index");
            System.out.println("3. Search (exact match)");
            System.out.println("4. Partial search (substring)");
            System.out.println("5. Regex search");
            System.out.println("6. Remove duplicates");
            System.out.println("7. Sort ascending");
            System.out.println("8. Sort descending");
            System.out.println("9. Sort by length");
            System.out.println("10. List starting with a letter");
            System.out.println("11. Display list");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter string to append: ");
                    ops.append(sc.nextLine());
                    break;
                case 2:
                    System.out.print("Enter index: ");
                    int idx = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter string to insert: ");
                    ops.insert(idx, sc.nextLine());
                    break;
                case 3:
                    System.out.print("Enter string to search: ");
                    System.out.println("Found(True/False): " + ops.search(sc.nextLine()));
                    break;
                case 4:
                    System.out.print("Enter substring: ");
                    System.out.println("Matches: " + ops.partialSearch(sc.nextLine()));
                    break;
                case 5:
                    System.out.print("Enter regex: ");
                    System.out.println("Regex matches: " + ops.regexSearch(sc.nextLine()));
                    break;
                case 6:
                    ops.removeDuplicates();
                    System.out.println("Duplicates removed.");
                    break;
                case 7:
                    ops.sortAsc();
                    System.out.println("Sorted ascending.");
                    break;
                case 8:
                    ops.sortDesc();
                    System.out.println("Sorted descending.");
                    break;
                case 9:
                    ops.sortByLength();
                    System.out.println("Sorted by length.");
                    break;
                case 10:
                    System.out.print("Enter starting letter: ");
                    char ch = sc.nextLine().charAt(0);
                    System.out.println("Matches: " + ops.startsWith(ch));
                    break;
                case 11:
                    System.out.println("List contents:");
                    ops.display();
                    break;
                case 0:
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 0);

        sc.close();
    }
}
