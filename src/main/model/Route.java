package model;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

// Represents a bus route with a direction, a name, and a list of stops on that route
public class Route {
    private int routeNumber;
    private String routeName;
    private List<Stop> stops;
    private List<String> operatorMessages;

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
        operatorMessages = new ArrayList<>();
    }

    // EFFECTS: returns a formatted string describing a bus route
    public String makeBusRoute() {
        return routeNumber + " " + routeName;
    }

    // REQUIRES: stop s is not null
    // MODIFIES: this
    // EFFECTS: adds stop s to this route's stop list, and logs this change 
    // in the EventLog
    public void addStop(Stop s) {
        if (s == null) {
            throw new IllegalArgumentException("Stop cannot be null");
        } else {
            stops.add(s);
            EventLog.getInstance().logEvent(
                new Event("Stop added to route " + routeNumber + " " + routeName + ": " + s.returnStop()));
        }
    }

    // EFFECTS: returns a list of formatted stops
    public String listStops() {
        if (stops.isEmpty()) {
            return "";
        } else {
            String accumulator = "";

            for (int i = 0; i < stops.size(); i++) {
                accumulator += stops.get(i).returnStop() + "\n";
            }
            return accumulator;
        }
    }

    // REQUIRES: msg is not an empty string
    // MODIFIES: this
    // EFFECTS: adds a new operator message to the list of messages, and logs this change
    // in the EventLog
    public void recordOperatorMessage(String msg) {
        if (msg.isEmpty()) {
            throw new IllegalArgumentException("Message is invalid");
        } else {
            operatorMessages.add(msg);
            EventLog.getInstance().logEvent(
                new Event("Operator message recorded for route " + routeNumber + " " + routeName + ": " + msg));
        }
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

    public List<String> getOperatorMessages() {
        return operatorMessages;
    }
    
    // MODIFIES: this
    // EFFECTS: returns JSON representation of this Route
    public JSONObject toJson() {
        JSONObject routeJson = new JSONObject();

        routeJson.put("routeNumber", this.routeNumber);
        routeJson.put("routeName", this.routeName);

        JSONArray msgArray = new JSONArray();
        for (String msg : operatorMessages) {
            msgArray.put(msg);
        }
        routeJson.put("operatorMessages", this.operatorMessages);

        JSONArray stopArray = new JSONArray();
        for (Stop s : stops) {
            stopArray.put(s.toJson());
        }
        routeJson.put("stops", stopArray);

        return routeJson;
    }
}
