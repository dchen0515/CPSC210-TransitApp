package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StopTest {
    private Stop s;
    
    @BeforeEach
    void runBefore() {
        s = new Stop("NB", "MAIN ST AT E 41 AVE", 50155, true);
    }

    @Test
    void testConstructorFields() {
        // stub
    }

    @Test
    void testConstructorRejectsInvalidDirection() {
        // stub
    }

    @Test
    void testConstructorRejectsInvalidStopID() {
        // stub
    }

    @Test
    void testReturnStopFormatting() {
        // stub
    }

    @Test
    void testModifyStopNameChangesName() {
        // stub
    }

    @Test
    void testModifyStopNameLeavesOtherFieldsUnchanged() {
        // stub
    }

    @Test
    void testModifyStopNameRejectsEmptyName() {
        // stub
    }

    @Test
    void testConstructorHandlesTimingPointFalse() {
        // stub
    }
}