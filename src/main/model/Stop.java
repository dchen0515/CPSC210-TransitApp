package model;

public class Stop {
    private String direction;
    private String stopName;
    private int stopID;
    private boolean isTimingPoint;

    public Stop(String direction, String stopName, int stopID, boolean isTimingPoint) {
        this.direction = direction;
        this.stopName = stopName;
        this.stopID = stopID;
        this.isTimingPoint = isTimingPoint;
    }

    // Return a formatted string for a bus stop
    public String returnStop() {
        return direction + " " + stopName + " - Stop number: " + stopID + " - Timing point? " + isTimingPoint;
    }
}
