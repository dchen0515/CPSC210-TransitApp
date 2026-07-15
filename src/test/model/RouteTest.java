package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RouteTest {
    private Route r;

    @BeforeEach
    void runBefore() {
        r = new Route(402, "TWO ROAD");
    }

    // REQUIRES: route number and route name are valid inputs
    // EFFECTS: confirms that all Route constructor fields initialize correctly
    @Test
    void testConstructorFields() {
        assertEquals(402, r.getRouteNumber());
        assertEquals("TWO ROAD", r.getRouteName());
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
        String result = r.makeBusRoute();
        assertTrue(result.contains("402"));
        assertTrue(result.contains("TWO ROAD"));
    }

    // REQUIRES: stop is not null
    // MODIFIES: this
    // EFFECTS: verifies that addStop adds the given stop to Route's stop list
    @Test
    void testAddStopAddsStop() {
        Stop s1 = new Stop("SB", "NO 2 RD AT 8600 BLOCK", 56000, false);
        r.addStop(s1);
        assertTrue(r.getStops().contains(s1));
    }

    // EFFECTS: verifies that addStop throws IllegalArgumentException when given
    // null
    @Test
    void testAddStopRejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            r.addStop(null);
        });
    }

    // REQUIRES: this Route has at least one stop
    // EFFECTS: verifies that listStops returns a formatted string with all stops
    @Test
    void testListStopsReturnsFormattedList() {
        Stop s2 = new Stop("WB", "BLUNDELL RD AT GILBERT RD", 56576, false);
        Stop s3 = new Stop("SB", "NO 2 RD AT WILLIAMS RD", 56585, false);
        r.addStop(s2);
        r.addStop(s3);
        String formattedListOfStops = r.listStops();
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
        String emptyStopList = r.listStops();
        assertEquals("", emptyStopList);
    }

    // REQUIRES: message is not an empty string
    // MODIFIES: this
    // EFFECTS: verifies that recordOperatorMessage stores the operator message
    // correctly
    @Test
    void testRecordOperatorMessageStoresMessage() {
        String msg = "Bus is too early!";
        r.recordOperatorMessage(msg);
        assertEquals(msg, r.getOperatorMessage());
    }

    // EFFECTS: verifies that recordOperatorMessage throws IllegalArgumentException
    // for empty messages
    @Test
    void testRecordOperatorMessageRejectsEmptyMessage() {
        assertThrows(IllegalArgumentException.class, () -> {
            r.recordOperatorMessage("");
        });
    }
}