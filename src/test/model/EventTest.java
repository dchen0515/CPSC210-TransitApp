package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * NOTE: this class is adapted from the EventTest class in the AlarmSystem project.
 * Source: CPSC 210 course staff, 2026
 */

/**
 * Unit tests for the Event class
 */
public class EventTest {
	private Event e;
	private Date d;
	
	//NOTE: these tests might fail if time at which line (2) below is executed
	//is different from time that line (1) is executed.  Lines (1) and (2) must
	//run in same millisecond for this test to make sense and pass.
	
	@BeforeEach
	public void runBefore() {
		e = new Event("TransitAppUI Event");   // (1)
		d = e.getDate();   // (2)
	}
	
	@Test
	public void testEvent() {
		assertEquals("TransitAppUI Event", e.getDescription());
		assertEquals(d, e.getDate());
	}

	@Test
	public void testToString() {
		assertEquals(d.toString() + "\n" + "TransitAppUI Event", e.toString());
	}
}
