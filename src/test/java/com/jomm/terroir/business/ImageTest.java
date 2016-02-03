package com.jomm.terroir.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * This class is a Junit test case testing the methods of {@link Image}.
 * @author Maic
 */
public class ImageTest {

	/**
	 * Test method for all {@link Image}'s getters and setters.
	 */
	@Test
	public final void testGetterSetter() {
		Image image = new Image();
		long nb = 0;
		
		// Id
		image.setId(nb);
		assertEquals("Id should be " + nb, nb, image.getId());
		
		// Blob
		// TODO fail("Blob: Not yet implemented"); 
		
		image = null; // Available for Garbage Collector
	}
}
