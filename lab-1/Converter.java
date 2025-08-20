import Currency.CurrencyConverter;
import Distance.DistanceConverter;
import Time.TimeConverter;
import java.util.Scanner;

public class Converter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CurrencyConverter cc = new CurrencyConverter();
        DistanceConverter dc = new DistanceConverter();
        TimeConverter tc = new TimeConverter();

        System.out.println("Choose Converter:\n1. Currency\n2. Distance\n3. Time");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Currency Options:\n1. Dollar to INR\n2. INR to Dollar\n3. Euro to INR\n4. INR to Euro\n5. Yen to INR\n6. INR to Yen");
                int c = sc.nextInt();
                System.out.print("Enter amount: ");
                double amount = sc.nextDouble();
                switch (c) {
                    case 1: System.out.println("Result: " + cc.dollarToInr(amount)); break;
                    case 2: System.out.println("Result: " + cc.inrToDollar(amount)); break;
                    case 3: System.out.println("Result: " + cc.euroToInr(amount)); break;
                    case 4: System.out.println("Result: " + cc.inrToEuro(amount)); break;
                    case 5: System.out.println("Result: " + cc.yenToInr(amount)); break;
                    case 6: System.out.println("Result: " + cc.inrToYen(amount)); break;
                    default: System.out.println("Invalid currency choice!");
                }
                break;

            case 2:
                System.out.println("Distance Options:\n1. Meter to KM\n2. KM to Meter\n3. Miles to KM\n4. KM to Miles");
                int d = sc.nextInt();
                System.out.print("Enter distance: ");
                double dist = sc.nextDouble();
                switch (d) {
                    case 1: System.out.println("Result: " + dc.meterToKm(dist)); break;
                    case 2: System.out.println("Result: " + dc.kmToMeter(dist)); break;
                    case 3: System.out.println("Result: " + dc.milesToKm(dist)); break;
                    case 4: System.out.println("Result: " + dc.kmToMiles(dist)); break;
                    default: System.out.println("Invalid distance choice!");
                }
                break;

            case 3:
                System.out.println("Time Options:\n1. Hours to Minutes\n2. Minutes to Hours\n3. Hours to Seconds\n4. Seconds to Hours");
                int t = sc.nextInt();
                System.out.print("Enter time: ");
                int time = sc.nextInt();
                switch (t) {
                    case 1: System.out.println("Result: " + tc.hoursToMinutes(time)); break;
                    case 2: System.out.println("Result: " + tc.minutesToHours(time)); break;
                    case 3: System.out.println("Result: " + tc.hoursToSeconds(time)); break;
                    case 4: System.out.println("Result: " + tc.secondsToHours(time)); break;
                    default: System.out.println("Invalid time choice!");
                }
                break;

            default:
                System.out.println("Invalid converter choice!");
        }

        sc.close();
    }
}
