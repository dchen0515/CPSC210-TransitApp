package model;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class RouteManager {
    private List<Route> routes;

    public RouteManager() {
        routes = new ArrayList<>();
    }

    public void addRoute(Route route) {
        routes.add(route);
    }

    public List<Route> getRoutes() {
        return routes;
    }

    // MODIFIES: this
    // EFFECTS: returns JSON representation of this RouteManager
    public JSONObject toJson() {
        JSONObject routeManagerJson = new JSONObject();

        JSONArray routeArray = new JSONArray();
        for (Route r : routes) {
            routeArray.put(r.toJson());
        }

        routeManagerJson.put("routes", routeArray);
        
        return routeManagerJson;
    }
}
