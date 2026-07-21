// NOTE: This class is based on the JsonReader class in the CPSC 210 JsonSerializationDemo project
// SOURCE: UBC CPSC 210 course staff (2026)

package persistence;

import model.Route;
import model.Stop;
import model.RouteManager;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads RouteManager from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads RouteManager from file and returns it;
    // throws IOException if an error occurs reading data from file
    public RouteManager read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseRouteManager(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses RouteManager from JSON object and returns it
    private RouteManager parseRouteManager(JSONObject jsonObject) {
        RouteManager rm = new RouteManager();
        addRoutes(rm, jsonObject);
        return rm;
    }

    // MODIFIES: rm
    // EFFECTS: parses routes from JSON object and adds them to the route manager
    private void addRoutes(RouteManager rm, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("routes");
        for (Object json : jsonArray) {
            JSONObject routeJson = (JSONObject) json;
            addRoute(rm, routeJson);
        }
    }

    // MODIFIES: rm
    // EFFECTS: parses route from JSON object and adds it to the route manager
    private void addRoute(RouteManager rm, JSONObject jsonObject) {
        int routeNumber = jsonObject.getInt("routeNumber");
        String routeName = jsonObject.getString("routeName");
        
        Route route = new Route(routeNumber, routeName);
        
        addStops(route, jsonObject);
        addOperatorMessage(route, jsonObject);

        rm.addRoute(route);
    }

    private void addStops(Route route, JSONObject routeJson) {
        JSONArray jsonArray = routeJson.getJSONArray("stops");
        for (Object json: jsonArray) {
            JSONObject stopJson = (JSONObject) json;

            int stopID = stopJson.getInt("stopID");
            String stopName = stopJson.getString("stopName");
            String direction = stopJson.getString("direction");
            boolean isTimingPoint = stopJson.getBoolean("isTimingPoint");

            Stop stop = new Stop(direction, stopName, stopID, isTimingPoint);
            route.addStop(stop);
        }
    }

    private void addOperatorMessage(Route route, JSONObject routeJson) {
        if (routeJson.has("operatorMessage")) {
            String message = routeJson.getString("operatorMessage");
            route.recordOperatorMessage(message);
        }
    }
}
