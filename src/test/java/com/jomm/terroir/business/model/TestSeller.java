package com.jomm.terroir.business.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This class is a Junit test case testing the methods of {@link Seller}.
 * @author Maic
 */
public class TestSeller {

	private Seller seller;
	
	/**
	 * Instantiate the {@link Seller}.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		seller = new Seller();
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		seller = null; // Available for Garbage Collector
	}
	
	/**
	 * Test constructor for {@link Site}.
	 */
	@Test
	public final void testConstructor() {
		assertNotNull("Constructor should instantiate the Enterprise", seller.getEnterprise());
	}
	
	/**
	 * Test method for all {@link Seller}'s getters and setters.
	 */
	@Test
	public final void testGetterSetter() {
		String test = "test";
		
		// Enterprise
		Enterprise enterprise = new Enterprise();
		enterprise.setLegalName(test);
		seller.setEnterprise(enterprise);
		assertNotNull("Enterprise should not be null", seller.getEnterprise());
		assertEquals("Enterprise.LegalName should be " + test, test, seller.getEnterprise().getLegalName());
		enterprise = null; // Available for Garbage Collector
	}
	
	/**
	 * Generate a simple {@link Seller} usable for tests.
	 * @return a {@link Seller}.
	 */
	public static Seller generateSellerWithIdNull() {
		Seller seller = new Seller();
		TestAbstractUser.setDummyValuesWithIdNull(seller);
		seller.setEnterprise(TestEnterprise.generateEnterpriseWithIdNull());
		return seller;
	}
}