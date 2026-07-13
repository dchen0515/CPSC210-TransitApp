package model;

// Represents a bus stop with a direction (NB, SB, EB, WB), name, stop ID, and timing point flag
// A timing point is a stop where buses must wait if they arrive ahead of schedule.
public class Stop {
    private String direction;
    private String stopName;
    private int stopID;
    private boolean isTimingPoint;

    // REQUIRES: direction is one of NB, SB, EB, or WB
    // EFFECTS: Constructs a bus stop object with a direction, stop name, stop ID, and a timing point flag
    public Stop(String direction, String stopName, int stopID, boolean isTimingPoint) {
        this.direction = direction;
        this.stopName = stopName;
        this.stopID = stopID;
        this.isTimingPoint = isTimingPoint;
    }

    // REQUIRES: stopID is a 5-digit positive integer beginning with either 5 or 6
    // EFFECTS: Return a formatted string describing a bus stop
    public String returnStop() {
        return direction + " " + stopName + " - Stop number: " + stopID + " - Timing point? " + isTimingPoint;
    }

    // MODIFIES: this
    // EFFECTS: changes a stop name to reflect changes that may apply to multiple routes in the system
    public void modifyStopName(String newName) {
         // stub
    }
}
