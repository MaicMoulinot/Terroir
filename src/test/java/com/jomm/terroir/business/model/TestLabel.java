package com.jomm.terroir.business.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This class is a Junit test case testing the methods of {@link Label}.
 * @author Maic
 */
public class TestLabel {
	
	private Label label;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		label = new Label();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		label = null; // Available for Garbage Collector
	}
	
	/**
	 * Test method for all {@link Product}'s getters and setters.
	 */
	@Test
	public final void testGetterSetter() {
		String test = "test";
		Long nb = (long) 0;
		
		// Id
		label.setId(nb);
		assertEquals("Id should be " + nb, nb, label.getId());
		
		// Name
		label.setName(test);
		assertEquals("Name should be " + test, test, label.getName());
		
		// Acronym
		label.setAcronym(test);
		assertEquals("Acronym should be " + test, test, label.getAcronym());
		
		// Definition
		label.setDefinition(test);
		assertEquals("Definition should be " + test, test, label.getDefinition());
		
		// Logo
		Image logo = new Image();
		logo.setId(nb);
		label.setLogo(logo);
		assertNotNull("Logo should not be null", label.getLogo());
		assertEquals("Logo's id should be " + nb, nb, label.getLogo().getId());
		logo = null; // Available for Garbage Collector
		
		// List of designations
		ArrayList<Designation> listDesignations = new ArrayList<>();
		Designation designation = new Designation();
		listDesignations.add(designation);
		label.setDesignations(listDesignations);
		assertNotNull("List of designations should not be null", label.getDesignations());
		assertFalse("List of designations should not be empty", label.getDesignations().isEmpty());
		assertEquals("List of designations size should be 1", 1, label.getDesignations().size());
		designation = null; // Available for Garbage Collector
		listDesignations = null; // Available for Garbage Collector
	}
	
	/**
	 * Generate a simple {@link Label} usable for tests.
	 * @return a {@link Label}.
	 */
	public static Label generateLabelWithIdNull() {
		Label label = new Label();
		label.setName("Name");
		label.setAcronym("Acronym");
		label.setDefinition("Definition");
		label.setLogo(TestImage.generateImageWithIdNull());
		label.setDesignations(new ArrayList<Designation>());
		return label;
	}
}