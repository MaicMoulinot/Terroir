package com.jomm.terroir.business.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This class is a Junit test case testing the methods of {@link Category}.
 * @author Maic
 */
public class TestCategory {
	
	private Category category;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		category = new Category();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		category = null; // Available for Garbage Collector
	}
	
	/**
	 * Test method for all {@link Product}'s getters and setters.
	 */
	@Test
	public final void testGetterSetter() {
		String test = "test";
		Long nb = (long) 0;
		
		// Id
		category.setId(nb);
		assertEquals("Id should be " + nb, nb, category.getId());
		
		// Name
		category.setName(test);
		assertEquals("Name should be " + test, test, category.getName());
		
		// Parent
		Category parent = new Category();
		parent.setId(nb);
		category.setParent(parent);
		assertNotNull("Parent should not be null", category.getParent());
		assertEquals("Parent's id should be " + nb, nb, category.getParent().getId());
		parent = null; // Available for Garbage Collector
		
		// List of designations
		ArrayList<Designation> listDesignations = new ArrayList<>();
		Designation designation = new Designation();
		listDesignations.add(designation);
		category.setDesignations(listDesignations);
		assertNotNull("List of designations should not be null", category.getDesignations());
		assertFalse("List of designations should not be empty", category.getDesignations().isEmpty());
		assertEquals("List of designations size should be 1", 1, category.getDesignations().size());
		designation = null; // Available for Garbage Collector
		listDesignations = null; // Available for Garbage Collector
	}
	
	/**
	 * Generate a simple {@link Category} usable for tests.
	 * @return a {@link Category}.
	 */
	public static Category generateCategoryWithIdNull() {
		Category category = new Category();
		category.setName("Name");
		category.setParent(null);
		category.setDesignations(new ArrayList<Designation>());
		return category;
	}
}