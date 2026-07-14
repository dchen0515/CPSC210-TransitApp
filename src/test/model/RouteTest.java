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

    @Test
    void testConstructorFields() {
        // stub
    }

    @Test
    void testConstructorRejectsInvalidRouteNumber() {
        // stub
    }

    @Test
    void testMakeBusRouteFormatting() {
        // stub
    }

    @Test
    void testAddStopAddsStop() {
        // stub
    }

    @Test
    void testAddStopRejectsNull() {
        // stub
    }

    @Test
    void testListStopsReturnsFormattedList() {
        // stub
    }

    @Test
    void testListStopsEmptyList() {
        // stub
    }

    @Test
    void testRecordOperatorMessageStoresMessage() {
        // stub
    }

    @Test
    void testRecordOperatorMessageRejectsEmptyMessage() {
        // stub
    }
}