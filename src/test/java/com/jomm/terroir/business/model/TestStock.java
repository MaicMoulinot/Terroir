package com.jomm.terroir.business.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.ZonedDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This class is a Junit test case testing the methods of {@link Stock}.
 * @author Maic
 */
public class TestStock {
	
	// Variables //-----------------------------------------------
	private Stock stock;
	
	// Test methods //--------------------------------------------
	/**
	 * Instantiate the {@link Stock}.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		stock = new Stock();
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		stock = null; // Available for Garbage Collector
	}

	/**
	 * Test method for all {@link Product}'s getters and setters.
	 */
	@Test
	public final void testGetterSetter() {
		Long nb = 0L;
		
		// Id
		stock.setId(nb);
		assertEquals("Id should be " + nb, nb, stock.getId());
		
		// Availability
		Integer quantity = 10;
		stock.setAvailability(quantity);
		assertEquals("Availability should be " + quantity, quantity, stock.getAvailability());
		
		// LastUpdate
		ZonedDateTime zonedDate = ZonedDateTime.now();
		stock.setLastUpdate(zonedDate);
		assertEquals("LastUpdate should be " + zonedDate, zonedDate, stock.getLastUpdate());
		
		// Product
		Product product = new Product();
		product.setId(nb);
		stock.setProduct(product);
		assertNotNull("Product should not be null", stock.getProduct());
		assertEquals("Product's id should be " + nb, nb, stock.getProduct().getId());
		product = null; // Available for Garbage Collector
	}
	
	// Static methods //------------------------------------------
	/**
	 * Generate a simple {@link Stock} usable for tests.
	 * @return a {@link Stock}.
	 */
	public static Stock generateStockWithIdNull() {
		Stock stock = new Stock(TestProduct.generateProductWithIdNull());
		stock.setAvailability(10);
		return stock;
	}
}