package com.jomm.terroir.business.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

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
		
		// LegalAct
		designation.setLegalAct(test);
		assertEquals("LegalAct should be " + test, test, designation.getLegalAct());
		
		// Definition
		designation.setDefinition(test);
		assertEquals("Definition should be " + test, test, designation.getDefinition());
		
		// Address
		Address address = new Address();
		address.setStreet(test);
		designation.setAddress(address);
		assertNotNull("Address should not be null", address);
		assertEquals("Address.Street should be " + test, test, designation.getAddress().getStreet());
		address = null; // Available for Garbage Collector
		
		// Label
		Label label = new Label();
		label.setId(nb);
		designation.setLabel(label);
		assertNotNull("Label should not be null", designation.getLabel());
		assertEquals("Label's id should be " + nb, nb, designation.getLabel().getId());
		label = null; // Available for Garbage Collector
		
		// Logo
		Image logo = new Image();
		logo.setId(nb);
		designation.setLogo(logo);
		assertNotNull("Logo should not be null", designation.getLogo());
		assertEquals("Logo's id should be " + nb, nb, designation.getLogo().getId());
		logo = null; // Available for Garbage Collector
		
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
		designation.setLegalAct("LegalAct");
		designation.setDefinition("Definition");
		designation.setAddress(TestAddress.generateAddress());
		designation.setLabel(TestLabel.generateLabelWithIdNull());
		designation.setLogo(null);
		designation.setProducts(new ArrayList<Product>());
		return designation;
	}
}