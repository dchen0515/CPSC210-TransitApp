package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StopTest {
    
    private Stop s;
    
    @BeforeEach
    void runBefore() {
        s = new Stop("NB", "MAIN ST AT E 41 AVE", 50155, true);
    }

    // REQUIRES: direction, stop name, and stop ID are valid
    // EFFECTS: verifies that all Stop constructor fields initialize correctly
    @Test
    void testConstructorFields() {
        assertEquals("NB", s.getDirection());
        assertEquals("MAIN ST AT E 41 AVE", s.getStopName());
        assertEquals(50155, s.getStopID());
        assertTrue(s.getIsTimingPoint());
    }

    // EFFECTS: verifies that the constructor throws IllegalArgumentException for invalid direction values
    @Test
    void testConstructorRejectsInvalidDirection() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Stop("NORTHBOUND", "MAIN ST AT E 41 AVE", 50155, true);
        });
    }

    // EFFECTS: verifies that the constructor throws IllegalArgumentException for invalid stop ID values
    @Test
    void testConstructorRejectsInvalidStopID() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Stop("NB", "MAIN ST AT E 41 AVE", 987654, true);
        });
    }

    // REQUIRES: direction, stop name, and stop ID are all valid inputs
    // EFFECTS: verifies that constructor correctly stores a false timing point value
    @Test
    void testConstructorHandlesTimingPointFalse() {
        Stop s1 = new Stop("NB", "MAIN ST AT E 57 AVE", 50147, false);
        assertFalse(s1.getIsTimingPoint());
    }

    // REQUIRES: this Stop has valid field values
    // EFFECTS: verifies that returnStop produces a formatted string with all stop information
    @Test
    void testReturnStopFormatting() {
        String result = s.returnStop();
        assertTrue(result.contains("NB"));
        assertTrue(result.contains("MAIN ST AT E 41 AVE"));
        assertTrue(result.contains("Stop number:"));
        assertTrue(result.contains("50155"));
        assertTrue(result.contains("Timing point?"));
        assertTrue(result.contains("true"));
    }

    // REQUIRES: newName is not an empty string
    // MODIFIES: this
    // EFFECTS: verifies that modifyStopName updates stop name correctly
    @Test
    void testModifyStopNameChangesName() {
        s.modifyStopName("MAIN ST AT 40 AVE");
        assertEquals("MAIN ST AT 40 AVE", s.getStopName());
    }

    // REQUIRES: newName is not an empty string
    // MODIFIES: this
    // EFFECTS: verifies that modifyStopName does not change direction, stop ID, or timing point status
    @Test
    void testModifyStopNameLeavesOtherFieldsUnchanged() {
        s.modifyStopName("MAIN ST AT 42 AVE");
        assertEquals("NB", s.getDirection());
        assertEquals(50155, s.getStopID());
        assertTrue(s.getIsTimingPoint());
    }

    // EFFECTS: verifies that modifyStopName throws IllegalArgumentException when given an empty string
    @Test
    void testModifyStopNameRejectsEmptyName() {
        assertThrows(IllegalArgumentException.class, () -> {
            s.modifyStopName("");;
        });
    }
}