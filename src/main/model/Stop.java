package model;

// Represents a bus stop with a direction (NB, SB, EB, WB), name, stop ID, and timing point flag
// A timing point is a stop where buses must wait if they arrive ahead of schedule.
public class Stop {
    private String direction;
    private String stopName;
    private int stopID;
    private boolean isTimingPoint;

    // REQUIRES: direction is one of NB, SB, EB, or WB, and stopID is a 5-digit positive integer 
    // beginning with either 5 or 6
    // EFFECTS: Constructs a bus stop object with a direction, stop name, stop ID, and a timing point flag
    public Stop(String direction, String stopName, int stopID, boolean isTimingPoint) {
        if (!(direction.equals("NB")) && !(direction.equals("SB")) && !(direction.equals("EB")) && 
        !(direction.equals("WB"))) {
            throw new IllegalArgumentException("Direction is not valid");
        }

        if (stopID < 50000 || stopID > 69999) {
            throw new IllegalArgumentException("Stop ID is not valid");
        }

        this.direction = direction;
        this.stopName = stopName;
        this.stopID = stopID;
        this.isTimingPoint = isTimingPoint;
    }

    // EFFECTS: Return a formatted string describing a bus stop
    public String returnStop() {
        return direction + " " + stopName + " - Stop number: " + stopID + " - Timing point? " + isTimingPoint;
    }

    // REQUIRES: newName is not an empty string
    // MODIFIES: this
    // EFFECTS: changes a stop name to reflect changes that may apply to multiple routes in the system
    public void modifyStopName(String newName) {
        if (newName.isEmpty()) {
            throw new IllegalArgumentException("New stop name is invalid");
        } else {
            this.stopName = newName;
        }
    }

    // Getters
    public String getDirection() {
        return direction;
    }

    public String getStopName() {
        return stopName;
    }

    public int getStopID() {
        return stopID;
    }

    public boolean getIsTimingPoint() {
        return isTimingPoint;
    }
}