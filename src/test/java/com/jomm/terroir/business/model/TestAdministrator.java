package com.jomm.terroir.business.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.jomm.terroir.business.model.Administrator;

/**
 * This class is a Junit test case testing the methods of {@link Administrator}.
 * @author Maic
 */
public class TestAdministrator {
	
	// Test methods //--------------------------------------------
	/**
	 * Test method for the {@link Administrator}'s constructor with no arg.
	 */
	@Test
	public final void testConstructor() {
		Administrator admin = new Administrator();
		assertNotNull(admin);
		assertFalse("CanReadData should be false", admin.canReadData());
		assertFalse("CanUpdateData should be false", admin.canUpdateData());
		assertFalse("CanDeleteData should be false", admin.canDeleteData());
		
		admin = null; // Available for Garbage Collector
	}
	
	/**
	 * Test method for all {@link Administrator}'s getters and setters.
	 */
	@Test
	public final void testGetterSetter() {
		Administrator admin = new Administrator();
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
	
	// Static methods //------------------------------------------
	/**
	 * Generate a simple {@link Administrator} usable for tests.
	 * @return a {@link Administrator}.
	 */
	public static Administrator generateAdministratorWithIdNull() {
		Administrator admin = new Administrator();
		TestAbstractUser.setDummyValuesWithIdNull(admin);
		return admin;
	}
}