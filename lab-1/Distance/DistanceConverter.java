package Distance;

public class DistanceConverter {
     public double meterToKm(double meter) {
        return meter / 1000;
    }

    public double kmToMeter(double km) {
        return km * 1000;
    }

    public double milesToKm(double miles) {
        return miles * 1.60934;
    }

    public double kmToMiles(double km) {
        return km / 1.60934;
    }
    
}
