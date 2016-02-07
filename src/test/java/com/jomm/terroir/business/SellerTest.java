package com.jomm.terroir.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * This class is a Junit test case testing the methods of {@link Seller}.
 * @author Maic
 */
public class SellerTest {

	/**
	 * Test method for all {@link Seller}'s getters and setters.
	 */
	@Test
	public final void testGetterSetter() {
		Seller seller = new Seller();
		String test = "test";
		
		// Enterprise
		Enterprise enterprise = new Enterprise();
		enterprise.setLegalName(test);
		seller.setEnterprise(enterprise);
		assertNotNull("Enterprise should not be null", seller.getEnterprise());
		assertEquals("Enterprise.LegalName should be " + test, test, seller.getEnterprise().getLegalName());
		enterprise = null; // Available for Garbage Collector
		
		seller = null; // Available for Garbage Collector
	}
	
	/**
	 * Generate a simple {@link Seller} usable for tests.
	 * @return a {@link Seller}.
	 */
	public static Seller generateSeller() {
		Seller seller = new Seller();
		seller.setId((long) 0);
		seller.setEmail("Email");
		seller.setFirstName("FirstName");
		seller.setLastName("LastName");
		seller.setUserName("UserName");
		seller.setUserPassword("UserPassword");
		seller.setEnterprise(new Enterprise());
		return seller;
	}
}