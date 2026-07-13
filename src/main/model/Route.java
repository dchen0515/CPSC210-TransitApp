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
}