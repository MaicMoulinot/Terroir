package com.jomm.terroir.business.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This class is a Junit test case testing the methods of {@link Designation}.
 * @author Maic
 */
public class TestDesignation {
	
	private Designation designation;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		designation = new Designation();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		designation = null; // Available for Garbage Collector
	}
	
	/**
	 * Test method for all {@link Product}'s getters and setters.
	 */
	@Test
	public final void testGetterSetter() {
		String test = "test";
		Long nb = (long) 0;
		
		// Id
		designation.setId(nb);
		assertEquals("Id should be " + nb, nb, designation.getId());
		
		// RegisteredName
		designation.setRegisteredName(test);
		assertEquals("RegisteredName should be " + test, test, designation.getRegisteredName());
		
		// TranscriptedName
		designation.setTranscriptedName(test);
		assertEquals("TranscriptedName should be " + test, test, designation.getTranscriptedName());
		
		// LocalName
		designation.setLocalName(test);
		assertEquals("LocalName should be " + test, test, designation.getLocalName());
		
		// LegalAct
		designation.setLegalAct(test);
		assertEquals("LegalAct should be " + test, test, designation.getLegalAct());
		
		// RegistrationDate
		ZonedDateTime zonedDate = ZonedDateTime.now();
		designation.setRegistrationDate(zonedDate);
		assertEquals("RegistrationDate should be " + zonedDate, zonedDate, designation.getRegistrationDate());
		
		// Definition
		designation.setDefinition(test);
		assertEquals("Definition should be " + test, test, designation.getDefinition());
		
		// Season
		designation.setSeason(test);
		assertEquals("Season should be " + test, test, designation.getSeason());
		
		// WebSite
		designation.setWebSite(test);
		assertEquals("WebSite should be " + test, test, designation.getWebSite());
		
		// MedianPrice
		BigDecimal price = new BigDecimal("1.23");
		designation.setMedianPrice(price);
		assertEquals("Price should be " + price.toString(), price, designation.getMedianPrice());
		
		// Address
		Address address = new Address();
		address.setStreet(test);
		designation.setAddress(address);
		assertNotNull("Address should not be null", address);
		assertEquals("Address.Street should be " + test, test, designation.getAddress().getStreet());
		address = null; // Available for Garbage Collector
		
		// Logo
		Image image = new Image();
		image.setId(nb);
		designation.setLogo(image);
		assertNotNull("Logo should not be null", designation.getLogo());
		assertEquals("Logo's id should be " + nb, nb, designation.getLogo().getId());
		
		// Picture
		designation.setPicture(image);
		assertNotNull("Picture should not be null", designation.getPicture());
		assertEquals("Picture's id should be " + nb, nb, designation.getPicture().getId());
		image = null; // Available for Garbage Collector
		
		// Category
		Category category = new Category();
		category.setId(nb);
		designation.setCategory(category);
		assertNotNull("Category should not be null", designation.getCategory());
		assertEquals("Category's id should be " + nb, nb, designation.getCategory().getId());
		category = null; // Available for Garbage Collector
		
		// List of labels
		ArrayList<Label> listLabels = new ArrayList<>();
		Label label = new Label();
		listLabels.add(label);
		designation.setLabels(listLabels);
		assertNotNull("List of labels should not be null", designation.getLabels());
		assertFalse("List of labels should not be empty", designation.getLabels().isEmpty());
		assertEquals("List of labels size should be 1", 1, designation.getLabels().size());
		label = null; // Available for Garbage Collector
		listLabels = null; // Available for Garbage Collector
		
		// List of products
		ArrayList<Product> listProducts = new ArrayList<>();
		Product product = new Product();
		listProducts.add(product);
		designation.setProducts(listProducts);
		assertNotNull("List of products should not be null", designation.getProducts());
		assertFalse("List of products should not be empty", designation.getProducts().isEmpty());
		assertEquals("List of products size should be 1", 1, designation.getProducts().size());
		product = null; // Available for Garbage Collector
		listProducts = null; // Available for Garbage Collector
	}
	
	/**
	 * Generate a simple {@link Designation} usable for tests.
	 * @return a {@link Designation}.
	 */
	public static Designation generateDesignationWithIdNull() {
		Designation designation = new Designation();
		designation.setRegisteredName("RegisteredName");
		designation.setTranscriptedName("TranscriptedName");
		designation.setLocalName("LocalName");
		designation.setLegalAct("LegalAct");
		designation.setRegistrationDate(ZonedDateTime.now());
		designation.setDefinition("Definition");
		designation.setSeason("Season");
		designation.setWebSite("WebSite");
		designation.setMedianPrice(new BigDecimal("1.2345"));
		designation.setAddress(TestAddress.generateAddress());
		designation.setLogo(TestImage.generateImageWithIdNull());
		designation.setPicture(TestImage.generateImageWithIdNull());
		designation.setCategory(TestCategory.generateCategoryWithIdNull());
		designation.setLabels(new ArrayList<Label>());
		designation.setProducts(new ArrayList<Product>());
		return designation;
	}
}