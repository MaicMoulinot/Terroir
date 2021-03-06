package com.jomm.terroir.business.model;

import static com.jomm.terroir.util.Constants.Unit.PIECE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jomm.terroir.util.Constants.Unit;

/**
 * This class is a Junit test case testing the methods of {@link Product}.
 * @author Maic
 */
public class TestProduct {
	
	// Variables //-----------------------------------------------
	private Product product;
	
	// Test methods //--------------------------------------------
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
	 * Test method for the {@link Product}'s constructor with no arg.
	 */
	@Test
	public final void testConstructor() {
		assertNotNull(product);
		assertNotNull(product.getStock());
		assertEquals(product, product.getStock().getProduct());
	}
	
	/**
	 * Test method for {@link Product#removeStock(Stock)}.
	 */
	@Test
	public final void testRemoveStock() {
		Stock stock = product.getStock();
		assertNotNull(stock);
		product.removeStock(stock);
		assertNull(stock.getProduct());
		assertNull(product.getStock());
	}
	
	/**
	 * Test method for all {@link Product}'s getters and setters.
	 */
	@Test
	public final void testGetterSetter() {
		String test = "test";
		Long nb = 0L;
		
		// Id
		product.setId(nb);
		assertEquals("Id should be " + nb, nb, product.getId());
		
		// Title
		product.setTitle(test);
		assertEquals("Title should be " + test, test, product.getTitle());
		
		// Quantity
		BigDecimal decimal = new BigDecimal("1.23");
		product.setQuantity(decimal);
		assertEquals("Quantity should be " + decimal.toString(), decimal, product.getQuantity());
		
		// Unit
		Unit unit = PIECE;
		product.setUnit(unit);
		assertEquals("Unit should be " + unit.toString(), unit, product.getUnit());
		unit = null; // Available for Garbage Collector
		
		// PricePerUnit
		product.setPricePerUnit(decimal);
		assertEquals("PricePerUnit should be " + decimal.toString(), decimal, product.getPricePerUnit());
		
		// TaxPercentage
		product.setTaxPercentage(decimal);
		assertEquals("TaxPercentage should be " + decimal.toString(), decimal, product.getTaxPercentage());
		decimal = null; // Available for Garbage Collector
		
		// Active
		Boolean active = true;
		product.setActive(active);
		assertEquals("Active should be " + active, active, product.isActive());
		active = null; // Available for Garbage Collector
		
		// Stock
		Stock stock = new Stock();
		Integer quantity = 11;
		stock.setAvailability(quantity);
		product.addStock(stock);
		assertNotNull("Stock should not be null", product.getStock());
		assertEquals("Stock's quantity should be " + quantity, quantity, product.getStock().getAvailability());
		stock = null; // Available for Garbage Collector
		
		// Site
		Site site = new Site();
		site.setId(nb);
		product.setSite(site);
		assertNotNull("Site should not be null", product.getSite());
		assertEquals("Site's id should be " + nb, nb, product.getSite().getId());
		site = null; // Available for Garbage Collector
		
		// Designation
		Designation designation = new Designation();
		designation.setId(nb);
		product.setDesignation(designation);
		assertNotNull("Designation should not be null", product.getDesignation());
		assertEquals("Designation's id should be " + nb, nb, product.getDesignation().getId());
		designation = null; // Available for Garbage Collector
	}
	
	// Static methods //------------------------------------------
	/**
	 * Generate a simple {@link Product} usable for tests.
	 * @return a {@link Product}.
	 */
	public static Product generateProductWithIdNull() {
		Product product = new Product();
		product.setTitle("Title");
		product.setQuantity(new BigDecimal("45"));
		product.setPricePerUnit(new BigDecimal("1.23"));
		product.setUnit(PIECE);
		product.setTaxPercentage(new BigDecimal("5.5"));
		product.setActive(true);
		product.setSite(TestSite.generateSiteWithIdNull());
		product.setDesignation(TestDesignation.generateDesignationWithIdNull());
		return product;
	}
}