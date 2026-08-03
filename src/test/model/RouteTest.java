package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class RouteTest {
    private Route route;

    @BeforeEach
    void runBefore() {
        route = new Route(402, "TWO ROAD");
    }

    // REQUIRES: route number and route name are valid inputs
    // EFFECTS: confirms that all Route constructor fields initialize correctly
    @Test
    void testConstructorFields() {
        assertEquals(402, route.getRouteNumber());
        assertEquals("TWO ROAD", route.getRouteName());
    }

    // EFFECTS: verifies that the constructor throws IllegalArgumentException for
    // invalid route numbers
    @Test
    void testConstructorRejectsInvalidRouteNumber() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Route(999, "BRIDGEPORT STN");
        });
    }

    // REQUIRES: this Route has valid route number and route name
    // EFFECTS: verifies that makeBusRoute correctly returns a formatted string
    // containing route information
    @Test
    void testMakeBusRouteFormatting() {
        String result = route.makeBusRoute();
        assertTrue(result.contains("402"));
        assertTrue(result.contains("TWO ROAD"));
    }

    // REQUIRES: stop is not null
    // MODIFIES: this
    // EFFECTS: verifies that addStop adds the given stop to Route's stop list
    @Test
    void testAddStopAddsStop() {
        Stop s1 = new Stop("SB", "NO 2 RD AT 8600 BLOCK", 56000, false);
        route.addStop(s1);
        assertTrue(route.getStops().contains(s1));
    }

    // EFFECTS: verifies that addStop throws IllegalArgumentException when given
    // null
    @Test
    void testAddStopRejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            route.addStop(null);
        });
    }

    // REQUIRES: this Route has at least one stop
    // EFFECTS: verifies that listStops returns a formatted string with all stops
    @Test
    void testListStopsReturnsFormattedList() {
        Stop s2 = new Stop("WB", "BLUNDELL RD AT GILBERT RD", 56576, false);
        Stop s3 = new Stop("SB", "NO 2 RD AT WILLIAMS RD", 56585, false);
        route.addStop(s2);
        route.addStop(s3);
        String formattedListOfStops = route.listStops();
        assertTrue(formattedListOfStops.contains("WB"));
        assertTrue(formattedListOfStops.contains("BLUNDELL RD AT GILBERT RD"));
        assertTrue(formattedListOfStops.contains("56576"));
        assertTrue(formattedListOfStops.contains("SB"));
        assertTrue(formattedListOfStops.contains("NO 2 RD AT WILLIAMS RD"));
        assertTrue(formattedListOfStops.contains("56585"));
    }

    // REQUIRES: this Route has no stops
    // EFFECTS: verifies that listStops returns an empty string when no stops are
    // present
    @Test
    void testListStopsEmptyList() {
        String emptyStopList = route.listStops();
        assertEquals("", emptyStopList);
    }

    // REQUIRES: message is not an empty string
    // MODIFIES: this
    // EFFECTS: verifies that recordOperatorMessage stores the operator message
    // correctly
    @Test
    void testRecordOperatorMessageStoresMessage() {
        String msg = "Bus is too early!";
        route.recordOperatorMessage(msg);
        assertEquals(1, route.getOperatorMessages().size());
        assertEquals(msg, route.getOperatorMessages().get(0));
    }

    @Test
    void testRecordMultipleOperatorMessages() {
        route.recordOperatorMessage("Message 1");
        route.recordOperatorMessage("Message 2");
        route.recordOperatorMessage("Message 3");

        assertEquals(3, route.getOperatorMessages().size());
        assertEquals("Message 1", route.getOperatorMessages().get(0));
        assertEquals("Message 2", route.getOperatorMessages().get(1));
        assertEquals("Message 3", route.getOperatorMessages().get(2));
    }

    // EFFECTS: verifies that recordOperatorMessage throws IllegalArgumentException
    // for empty messages
    @Test
    void testRecordOperatorMessageRejectsEmptyMessage() {
        assertThrows(IllegalArgumentException.class, () -> {
            route.recordOperatorMessage("");
        });
    }
}