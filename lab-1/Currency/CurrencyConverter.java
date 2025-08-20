package Currency;

public class CurrencyConverter {
    public double dollarToInr(double dollar) {
        return dollar * 83.2;
    }

    public double inrToDollar(double inr) {
        return inr / 83.2;
    }

    public double euroToInr(double euro) {
        return euro * 90.5;
    }

    public double inrToEuro(double inr) {
        return inr / 90.5;
    }

    public double yenToInr(double yen) {
        return yen * 0.56;
    }

    public double inrToYen(double inr) {
        return inr / 0.56;
    }
}
