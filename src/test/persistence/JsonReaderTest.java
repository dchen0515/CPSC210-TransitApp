// NOTE: This class is based on the JsonReaderTest class in the CPSC 210 JsonSerializationDemo project
// SOURCE: UBC CPSC 210 course staff (2026)

package persistence;

import model.Route;
import model.Stop;
import model.RouteManager;
import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExcludeFromJacocoGeneratedReport
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            RouteManager rm = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyRouteManager() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyRouteManager.json");
        try {
            RouteManager rm = reader.read();
            assertEquals(0, rm.getRoutes().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralRouteManager() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralRouteManager.json");
        try {
            RouteManager rm = reader.read();
            List<Route> routes = rm.getRoutes();
            assertEquals(1, routes.size());
            
            Route r = routes.get(0);
            assertEquals(402, r.getRouteNumber());
            assertEquals("TWO ROAD", r.getRouteName());

            List<Stop> stops = r.getStops();
            assertEquals(2, stops.size());

            checkStop(56789, "NB", "Fraser St", false, stops.get(0));
            checkStop(67890, "EB", "41st Ave", true, stops.get(1));

            assertEquals("5-min delay on Cambie St due to traffic", r.getOperatorMessage());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}