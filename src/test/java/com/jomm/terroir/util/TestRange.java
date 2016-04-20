package com.jomm.terroir.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Maic
 *
 */
public class TestRange {
	
	private BigDecimal min;
	private BigDecimal max;
	private Range range;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		min = new BigDecimal("1.23");
		max = new BigDecimal("2.456");
		range = new Range(min, max);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		min = null; // Available for Garbage Collector
		max = null; // Available for Garbage Collector
		range = null; // Available for Garbage Collector
	}
	
	/**
	 * Test method for the {@link Range#Range(BigDecimal, BigDecimal)}.
	 */
	@Test
	public final void testConstructor() {
		assertNotNull(range);
		assertEquals("Min should be", min, range.getMinimum());
		assertEquals("Max should be", max, range.getMaximum());
	}

	/**
	 * Test method for all {@link Range}'s getters and setters.
	 */
	@Test
	public final void testGettersSetters() {
		// Minimum
		range.setMinimum(min);
		assertEquals("Minimum should be " + min, min, range.getMinimum());
		
		// Maximum
		range.setMaximum(max);
		assertEquals("Maximum should be " + max, max, range.getMaximum());
	}
}