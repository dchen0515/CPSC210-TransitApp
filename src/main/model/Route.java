package model;

public class Route {
    private int routeNumber;
    private String routeName;

    public Route(int routeNumber, String routeName) {
        this.routeNumber = routeNumber;
        this.routeName = routeName;
    }

    // return a formatted string for a bus route
    public String makeBusRoute() {
        return routeNumber + " " + routeName;
    }
}