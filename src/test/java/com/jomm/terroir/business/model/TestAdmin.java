package com.jomm.terroir.business.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.jomm.terroir.business.model.Admin;

/**
 * This class is a Junit test case testing the methods of {@link Admin}.
 * @author Maic
 */
public class TestAdmin {

	/**
	 * Test method for the {@link Admin}'s constructor with no arg.
	 */
	@Test
	public final void testConstructor() {
		Admin admin = new Admin();
		assertNotNull(admin);
		assertFalse("CanReadData should be false", admin.canReadData());
		assertFalse("CanUpdateData should be false", admin.canUpdateData());
		assertFalse("CanDeleteData should be false", admin.canDeleteData());
		
		admin = null; // Available for Garbage Collector
	}
	
	/**
	 * Test method for all {@link Admin}'s getters and setters.
	 */
	@Test
	public final void testGetterSetter() {
		Admin admin = new Admin();
		boolean test = true;
		
		// CanReadData
		admin.setCanReadData(test);
		assertEquals("CanReadData should be " + test, test, admin.canReadData());
		
		// CanUpdateData
		admin.setCanUpdateData(test);
		assertEquals("CanUpdateData should be " + test, test, admin.canUpdateData());
		
		// CanDeleteDate
		admin.setCanDeleteData(test);
		assertEquals("CanDeleteDate should be " + test, test, admin.canDeleteData());
		
		admin = null; // Available for Garbage Collector
	}
	
	/**
	 * Generate a simple {@link Admin} usable for tests.
	 * @return a {@link Admin}.
	 */
	public static Admin generateAdminWithIdNull() {
		Admin admin = new Admin();
		TestAbstractUser.setDummyValuesWithIdNull(admin);
		return admin;
	}
}