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

	private Site site;
	
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
	 * Test constructor for {@link Site}.
	 */
	@Test
	public final void testConstructor() {
		assertNotNull("Constructor should instantiate the Address", site.getAddress());
		assertNotNull("Constructor should instantiate the Enterprise", site.getEnterprise());
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
		ArrayList<Product> listProducts = new ArrayList<>();
		Product product = new Product();
		listProducts.add(product);
		site.setListProducts(listProducts);
		assertNotNull("ListProducts should not be null", site.getListProducts());
		assertFalse("ListProducts should not be empty", site.getListProducts().isEmpty());
		assertEquals("ListProducts' size should be 1", 1, site.getListProducts().size());
		product = null; // Available for Garbage Collector
		listProducts = null; // Available for Garbage Collector
	}
	
	/**
	 * Generate a simple {@link Site} usable for tests.
	 * @return a {@link Site}.
	 */
	public static Site generateSiteWithIdNull() {
		Site site = new Site();
		site.setAddress(TestAddress.generateAddress());
		site.setEnterprise(TestEnterprise.generateEnterpriseWithIdNull());
		site.setLegalIdentification("LegalIdentification");
		site.setListProducts(new ArrayList<>());
		site.setSiteName("SiteName");
		return site;
	}
}