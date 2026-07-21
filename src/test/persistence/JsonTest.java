// NOTE: This class is based on the JsonTest class in the CPSC 210 JsonSerializationDemo project
// SOURCE: UBC CPSC 210 course staff (2026)

package persistence;

import model.Stop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class JsonTest {
    protected void checkStop(int id, String direction, String name, boolean isTimingPoint, Stop stop) {
        assertEquals(id, stop.getStopID());
        assertEquals(direction, stop.getDirection());
        assertEquals(name, stop.getStopName());
        assertEquals(isTimingPoint, stop.getIsTimingPoint());
    }
}
