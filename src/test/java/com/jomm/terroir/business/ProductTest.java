package com.jomm.terroir.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.ZonedDateTime;

import org.junit.Test;

/**
 * This class is a Junit test case testing the methods of {@link Product}.
 * @author Maic
 */
public class ProductTest {

	/**
	 * Test method for all {@link Product}'s getters and setters.
	 */
	@Test
	public final void testGetterSetter() {
		Product product = new Product();
		String test = "test";
		long nb = 0;
		
		// Id
		product.setId(nb);
		assertEquals("Id should be " + nb, nb, product.getId());
		
		// Title
		product.setTitle(test);
		assertEquals("Title should be " + test, test, product.getTitle());
		
		// Description
		product.setDescription(test);
		assertEquals("Description should be " + test, test, product.getDescription());
		
		// Quantity
		int qty = 1;
		product.setQuantity(qty);
		assertEquals("Quantity should be " + qty, qty, product.getQuantity());
		
		// RegistrationDate
		ZonedDateTime zonedDate = ZonedDateTime.now();
		product.setRegistrationDate(zonedDate);
		assertEquals("RegistrationDate should be " + zonedDate, zonedDate, product.getRegistrationDate());
		
		// Site
		Site site = new Site();
		site.setId(nb);
		product.setSite(site);
		assertNotNull("Site should not be null", product.getSite());
		assertEquals("Site's id should be " + nb, nb, product.getSite().getId());
		site = null; // Available for Garbage Collector
		
		product = null; // Available for Garbage Collector
	}
	
	/**
	 * Generate a simple {@link Product} usable for tests.
	 * @return a {@link Product}.
	 */
	public static Product generateProduct() {
		Product product = new Product();
		product.setId((long) 0);
		product.setDescription("Description");
		product.setQuantity(10);
		product.setSite(new Site());
		product.setTitle("Title");
		product.setRegistrationDate(ZonedDateTime.now());
		return product;
	}
}
