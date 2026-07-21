package model;

import java.util.List;
import java.util.ArrayList;

public class RouteManager {
    private List<Route> routes;

    public RouteManager() {
       routes = new ArrayList<>();
    }

    public void addRoute(Route route) {
        routes.add(route);
    }
}
