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
		Long nb = 0L;
		
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
		
		// List of images
		ArrayList<Image> listImages = new ArrayList<>();
		Image image = new Image();
		listImages.add(image);
		site.setImages(listImages);
		assertNotNull("List of images should not be null", site.getImages());
		assertFalse("List of images should not be empty", site.getImages().isEmpty());
		assertEquals("List of images size should be 1", 1, site.getImages().size());
		image = null; // Available for Garbage Collector
		listImages = null; // Available for Garbage Collector
		
		// List of designations
		ArrayList<Designation> listDesignations = new ArrayList<>();
		Designation designation = new Designation();
		listDesignations.add(designation);
		site.setDesignations(listDesignations);
		assertNotNull("List of designations should not be null", site.getDesignations());
		assertFalse("List of designations should not be empty", site.getDesignations().isEmpty());
		assertEquals("List of designations size should be 1", 1, site.getDesignations().size());
		designation = null; // Available for Garbage Collector
		listDesignations = null; // Available for Garbage Collector
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
		site.setDesignations(new ArrayList<Designation>());
		return site;
	}
}