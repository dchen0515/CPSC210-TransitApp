package model;

import java.util.List;
import java.util.ArrayList;

// Represents a bus route with a direction, a name, and a list of stops on that route
public class Route {
    private int routeNumber;
    private String routeName;
    private List<Stop> stops;
    private String operatorMessage;

    // REQUIRES: routeNumber is between 1 and 900 inclusive
    // EFFECTS: constructs a Route object with a route number, route name, and list
    // of stops
    public Route(int routeNumber, String routeName) {
        if (routeNumber < 1 || routeNumber > 900) {
            throw new IllegalArgumentException("Invalid route number");
        }

        this.routeNumber = routeNumber;
        this.routeName = routeName;
        stops = new ArrayList<>();
    }

    // EFFECTS: returns a formatted string describing a bus route
    public String makeBusRoute() {
        return routeNumber + " " + routeName;
    }

    // REQUIRES: stop s is not null
    // MODIFIES: this
    // EFFECTS: adds stop s to this route's stop list
    public void addStop(Stop s) {
        if (s == null) {
            throw new IllegalArgumentException("Stop cannot be null");
        } else {
            stops.add(s);
        }
    }

    // EFFECTS: returns a list of formatted stops
    public String listStops() {
        if (stops.isEmpty()) {
            return "";
        } else {
            String accumulator = "";

            for (int i = 0; i < stops.size(); i++) {
                accumulator += stops.get(i).returnStop();
            }
            return accumulator;
        }
    }

    // REQUIRES: msg is not an empty string
    // MODIFIES: this
    // EFFECTS: simulates recording a message from the operator
    public void recordOperatorMessage(String msg) {
        if (msg.isEmpty()) {
            throw new IllegalArgumentException("Message is invalid");
        } else {
            operatorMessage = msg;
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the operator message for this route
    public void setOperatorMessage(String msg) {
        this.operatorMessage = msg;
    }

    // Getters
    public int getRouteNumber() {
        return routeNumber;
    }

    public String getRouteName() {
        return routeName;
    }

    public List<Stop> getStops() {
        return stops;
    }

    public String getOperatorMessage() {
        return operatorMessage;
    }
}