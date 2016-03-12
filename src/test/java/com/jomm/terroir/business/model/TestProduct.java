package com.jomm.terroir.business.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.ZonedDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This class is a Junit test case testing the methods of {@link Product}.
 * @author Maic
 */
public class TestProduct {
	
	private Product product;
	
	/**
	 * Instantiate the {@link Product}.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		product = new Product();
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		product = null; // Available for Garbage Collector
	}

	/**
	 * Test method for all {@link Product}'s getters and setters.
	 */
	@Test
	public final void testGetterSetter() {
		String test = "test";
		Long nb = (long) 0;
		
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
	}
	
	/**
	 * Generate a simple {@link Product} usable for tests.
	 * @return a {@link Product}.
	 */
	public static Product generateProductWithIdNull() {
		Product product = new Product();
		product.setDescription("Description");
		product.setQuantity(10);
		product.setSite(TestSite.generateSiteWithIdNull());
		product.setTitle("Title");
		return product;
	}
}