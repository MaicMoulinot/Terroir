package com.jomm.terroir.business.model;

import static org.junit.Assert.assertEquals;

import java.awt.image.BufferedImage;

import org.junit.Test;

/**
 * This class is a Junit test case testing the methods of {@link Image}.
 * @author Maic
 */
public class TestImage {

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
		
		// BufferedImage
		BufferedImage bufferedImage = new BufferedImage(10, 10, BufferedImage.TRANSLUCENT);
		image.setBufferedImage(bufferedImage);
		assertEquals("BufferedImage should be " + bufferedImage, bufferedImage, image.getBufferedImage());
		bufferedImage = null; // Available for Garbage Collector
		
		image = null; // Available for Garbage Collector
	}
	
	/**
	 * Generate a simple {@link Image} usable for tests.
	 * @return a {@link Image}.
	 */
	public static Image generateImageWithIdNull() {
		Image image = new Image();
		image.setTitle("Title");
		image.setDescription("Description");
		image.setBufferedImage(new BufferedImage(10, 10, BufferedImage.TRANSLUCENT));
		return image;
	}
}