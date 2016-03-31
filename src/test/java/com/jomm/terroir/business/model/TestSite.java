package com.jomm.terroir.business.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This class is a Junit test case testing the methods of {@link Site}.
 * @author Maic
 */
public class TestSite {
	
	// Variables //-----------------------------------------------
	private Site site;
	
	// Test methods //--------------------------------------------
	/**
	 * Instantiate the {@link Site}.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		site = new Site();
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		site = null; // Available for Garbage Collector
	}
	
	/**
	 * Test method for all {@link Site}'s getters and setters.
	 */
	@Test
	public final void testGetterSetter() {
		String test = "test";
		Long nb = (long) 0;
		
		// Id
		site.setId(nb);
		assertEquals("Id should be " + nb, nb, site.getId());
		
		// Name
		site.setName(test);
		assertEquals("SiteName should be " + test, test, site.getName());
		
		// LegalIdentification
		site.setLegalIdentification(test);
		assertEquals("LegalIdentification should be " + test, test, site.getLegalIdentification());
		
		// Description
		site.setDescription(test);
		assertEquals("Description should be " + test, test, site.getDescription());
		
		// Address
		Address address = new Address();
		address.setStreet(test);
		site.setAddress(address);
		assertNotNull("Address should not be null", address);
		assertEquals("Address.Street should be " + test, test, site.getAddress().getStreet());
		address = null; // Available for Garbage Collector
		
		// Enterprise
		Enterprise enterprise = new Enterprise();
		enterprise.setLegalName(test);
		site.setEnterprise(enterprise);
		assertNotNull("Enterprise should not be null", site.getEnterprise());
		assertEquals("Enterprise.LegalName should be " + test, test, site.getEnterprise().getLegalName());
		enterprise = null; // Available for Garbage Collector
		
		// List of products
		ArrayList<Product> listProducts = new ArrayList<>();
		Product product = new Product();
		listProducts.add(product);
		site.setProducts(listProducts);
		assertNotNull("List of products should not be null", site.getProducts());
		assertFalse("List of products should not be empty", site.getProducts().isEmpty());
		assertEquals("List of products size should be 1", 1, site.getProducts().size());
		product = null; // Available for Garbage Collector
		listProducts = null; // Available for Garbage Collector
	}
	
	// Static methods //------------------------------------------
	/**
	 * Generate a simple {@link Site} usable for tests.
	 * @return a {@link Site}.
	 */
	public static Site generateSiteWithIdNull() {
		Site site = new Site();
		site.setName("SiteName");
		site.setLegalIdentification("LegalIdentification");
		site.setDescription("Description");
		site.setAddress(TestAddress.generateAddress());
		site.setEnterprise(TestEnterprise.generateEnterpriseWithIdNull());
		site.setProducts(new ArrayList<Product>());
		site.setImages(new ArrayList<Image>());
		return site;
	}
}