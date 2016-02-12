package com.jomm.terroir.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Test;

/**
 * This class is a Junit test case testing the methods of {@link Site}.
 * @author Maic
 */
public class SiteTest {

	/**
	 * Test method for all {@link Site}'s getters and setters.
	 */
	@Test
	public final void testGetterSetter() {
		Site site = new Site();
		String test = "test";
		Long nb = (long) 0;
		
		// Id
		site.setId(nb);
		assertEquals("Id should be " + nb, nb, site.getId());
		
		// SiteName
		site.setSiteName(test);
		assertEquals("SiteName should be " + test, test, site.getSiteName());
		
		// LegalIdentification
		site.setLegalIdentification(test);
		assertEquals("LegalIdentification should be " + test, test, site.getLegalIdentification());
		
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
		
		// ListProducts
		ArrayList<Product> listProducts = new ArrayList<Product>();
		Product product = new Product();
		listProducts.add(product);
		site.setListProducts(listProducts);
		assertNotNull("ListProducts should not be null", site.getListProducts());
		assertFalse("ListProducts should not be empty", site.getListProducts().isEmpty());
		assertEquals("ListProducts' size should be 1", 1, site.getListProducts().size());
		product = null; // Available for Garbage Collector
		listProducts = null; // Available for Garbage Collector
		
		site = null; // Available for Garbage Collector
	}
	
	/**
	 * Generate a simple {@link Site} usable for tests.
	 * @return a {@link Site}.
	 */
	public static Site generateSite() {
		Site site = new Site();
		site.setAddress(new Address());
		site.setEnterprise(EnterpriseTest.generateEnterprise());
		site.setLegalIdentification("LegalIdentification");
		site.setListProducts(new ArrayList<Product>());
		site.setSiteName("SiteName");
		return site;
	}
}