// NOTE: This class is based on the JsonWriterTest class in the CPSC 210 JsonSerializationDemo project
// SOURCE: UBC CPSC 210 course staff (2026)

package persistence;

import model.RouteManager;
import model.Route;
import model.Stop;
import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExcludeFromJacocoGeneratedReport
class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            RouteManager rm = new RouteManager();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyRouteManager.json");
            writer.open();
            writer.write(rm);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyRouteManager.json");
            rm = reader.read();
            assertEquals(0, rm.getRoutes().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            RouteManager rm = new RouteManager();

            Route r = new Route(402, "TWO ROAD");
            r.setOperatorMessage("5-min delay on Cambie St due to traffic");
            r.addStop(new Stop("NB", "Fraser St", 56789, false));
            r.addStop(new Stop("EB", "41st Ave", 67890, true));

            rm.addRoute(r);
            
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(rm);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            rm = reader.read();

            List<Route> routes = rm.getRoutes();
            assertEquals(1, routes.size());
            
            Route readRoute = routes.get(0);
            assertEquals(402, readRoute.getRouteNumber());
            assertEquals("TWO ROAD", readRoute.getRouteName());
            assertEquals("5-min delay on Cambie St due to traffic", readRoute.getOperatorMessage());

            List<Stop> stops = readRoute.getStops();
            assertEquals(2, stops.size());

            checkStop("NB", "Fraser St", 56789, false, stops.get(0));
            checkStop("EB", "41st Ave", 67890, true, stops.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
