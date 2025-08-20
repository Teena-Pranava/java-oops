package Time;

public class TimeConverter {
    public int hoursToMinutes(int hours) {
        return hours * 60;
    }

    public double minutesToHours(int minutes) {
        return minutes / 60.0;
    }

    public int hoursToSeconds(int hours) {
        return hours * 3600;
    }

    public double secondsToHours(int seconds) {
        return seconds / 3600.0;
    }
    
}
