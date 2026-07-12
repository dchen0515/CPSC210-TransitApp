package model;

public class Route {
    private int routeNumber = 402;
    private String routeName = "TWO ROAD";

    public Route(int routeNumber, String routeName) {
        this.routeNumber = routeNumber;
        this.routeName = routeName;
    }

    public String makeBusRoute() {
        return routeNumber + " " + routeName;
    }
}