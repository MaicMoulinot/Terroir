package com.jomm.terroir.business.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

/**
 * This class is a Junit test case testing the methods of {@link Image}.
 * @author Maic
 */
public class TestImage {
	
	// Constants //-----------------------------------------------
	private static final String IMAGE_RELATIVE_PATH = "../../../../../resources/image/terroir.png";
	
	// Test methods //--------------------------------------------
	/**
	 * Test method for all {@link Image}'s getters and setters.
	 */
	@Test
	public final void testGetterSetter() {
		Image image = new Image();
		Long nb = (long) 0;
		String test = "test";

		// Id
		image.setId(nb);
		assertEquals("Id should be " + nb, nb, image.getId());

		// Title
		image.setTitle(test);
		assertEquals("Title should be " + test, test, image.getTitle());

		// Description
		image.setDescription(test);
		assertEquals("Description should be " + test, test, image.getDescription());

		// Data
		byte[] data = new byte[2];
		image.setData(data);
		assertEquals("Data should be " + data, data, image.getData());
		
		image = null; // Available for Garbage Collector
	}
	
	// Static methods //------------------------------------------
	/**
	 * Generate a simple {@link Image} usable for tests.
	 * @return a {@link Image}.
	 */
	public static Image generateImageWithIdNull() {
		Image image = new Image();
		image.setTitle("Title");
		image.setDescription("Description");
		image.setData(getDataFromFile());
		return image;
	}
	
	/**
	 * Construct a array of bytes from a file, and test its integrity.
	 * @return byte[] the array of bytes.
	 */
	private static byte[] getDataFromFile() {
		byte[] data = null;
		InputStream input = TestImage.class.getResourceAsStream(IMAGE_RELATIVE_PATH);
		assertNotNull("InputStream should not be null", input);
		try {
			data = IOUtils.toByteArray(input);
			assertNotNull("Data should not be null", data);
			assertEquals("Data's length should be", 33783, data.length);
		} catch (Exception exception) {
			fail("Data should not be null, " + exception.getMessage());
		}
		return data;
	}
}