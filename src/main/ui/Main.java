package ui;

import model.Route;
import model.Stop;

public class Main {
    public static void main(String[] args) {
        // Print out sample 402 route
        Route r402 = new Route(402, "TWO ROAD");
        System.out.println(r402.makeBusRoute());

        // Print out sample stop description
        Stop s56587 = new Stop("SB", "No. 2 Rd at Steveston Hwy", 56587, false);
        System.out.println(s56587.returnStop());
    }
}