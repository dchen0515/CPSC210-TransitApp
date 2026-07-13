package model;

// Represents a bus route with a direction and a name
public class Route {
    private int routeNumber;
    private String routeName;

    // REQUIRES: routeNumber is a valid TransLink bus route number
    // EFFECTS: constructs a Route object with a route number and route name
    public Route(int routeNumber, String routeName) {
        this.routeNumber = routeNumber;
        this.routeName = routeName;
    }

    // EFFECTS: returns a formatted string describing a bus route
    public String makeBusRoute() {
        return routeNumber + " " + routeName;
    }

    // MODIFIES: this
    // EFFECTS: adds stop s to this route's stop list
    public void addStop(Stop s) {
        // stub
    }

    // EFFECTS: returns a list of formatted stops
    public void listStops() {
        // stub
    }

    // REQUIRES: msg is not an empty string
    // MODIFIES: this
    // EFFECTS: simulates recording a message from the operator
    public void recordOperatorMessage(String msg) {
        // stub
    }
}