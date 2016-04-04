package com.jomm.terroir.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.jomm.terroir.business.model.Enterprise;
import com.jomm.terroir.business.model.Seller;
import com.jomm.terroir.business.model.TestEnterprise;

/**
 * This class is a Junit test case testing {@link BackingRegistrationSeller}.
 * @author Maic
 */
public class TestBackingRegistrationSeller {
	
	// Variables //-----------------------------------------------
	private BackingRegistrationSeller view;
	
	// Test methods //--------------------------------------------
	/**
	 * Test method for {@link BackingRegistrationSeller#init()}.
	 */
	@Test
	public final void testInit() {
		view = new BackingRegistrationSeller();
		assertNull(view.getEnterprise());
		view.init();
		assertNotNull(view.getEnterprise());
	}

	/**
	 * Test method for {@link BackingRegistrationSeller#convertIntoEntity()}.
	 */
	@Test
	public final void testConvertIntoEntity() {
		view = generateDummyViewSeller();
		Seller entity = view.convertIntoEntity();
		compareViewAndEntity(view, entity);
	}

	/**
	 * Test method for {@link BackingRegistrationSeller}'s getters and setters.
	 */
	@Test
	public final void testGetterSetter() {
		view = new BackingRegistrationSeller();
		Enterprise enterprise = TestEnterprise.generateEnterpriseWithIdNull();
		view.setEnterprise(enterprise);
		assertEquals("Enterprise should be " + enterprise, enterprise, view.getEnterprise());
	}	

	// Helpers //-------------------------------------------------
	/**
	 * Compare a view and an entity.
	 * @param view {@link BackingRegistrationSeller}.
	 * @param entity {@link Seller}.
	 */
	private void compareViewAndEntity(BackingRegistrationSeller view, Seller entity) {
		assertEquals(view.getFirstName(), entity.getFirstName());
		assertEquals(view.getLastName(), entity.getLastName());
		assertEquals(view.getUserName(), entity.getUserName());
		assertEquals(view.getPassword(), String.valueOf(entity.getPassword()));
		assertEquals(view.getEmail(), entity.getEmail());
		assertEquals(view.getEnterprise(), entity.getEnterprise());
	}

	// Static methods //------------------------------------------
	/**
	 * Generate a dummy {@link BackingRegistrationSeller} usable for tests.
	 * @return {@link BackingRegistrationSeller}.
	 */
	static BackingRegistrationSeller generateDummyViewSeller() {
		BackingRegistrationSeller view = new BackingRegistrationSeller();
		TestBackingRegistrationUser.setDummyValues(view);
		view.setEnterprise(TestEnterprise.generateEnterpriseWithIdNull());
		return view;
	}
}