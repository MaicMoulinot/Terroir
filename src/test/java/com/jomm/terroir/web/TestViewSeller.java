package com.jomm.terroir.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.jomm.terroir.business.model.Enterprise;
import com.jomm.terroir.business.model.Seller;
import com.jomm.terroir.business.model.TestEnterprise;
import com.jomm.terroir.business.model.TestSeller;

/**
 * This class is a Junit test case testing {@link ViewSeller}.
 * @author Maic
 */
public class TestViewSeller {

	private ViewSeller view;
	
	/**
	 * Test method for {@link ViewSeller#init()}.
	 */
	@Test
	public final void testInit() {
		view = new ViewSeller();
		assertNull(view.getEnterprise());
		view.init();
		assertNotNull(view.getEnterprise());
	}

	/**
	 * Test method for {@link ViewSeller#convertIntoEntity()}.
	 */
	@Test
	public final void testConvertIntoEntity() {
		view = generateDummyViewSeller();
		view.setId((long) 3333);
		Seller entity = view.convertIntoEntity();
		compareViewAndEntity(view, entity);
	}

	/**
	 * Test method for {@link ViewSeller#convertIntoView(Seller)}.
	 */
	@Test
	public final void testConvertIntoView() {
		Seller entity = TestSeller.generateSellerWithIdNull();
		entity.setId((long) 3333);
		ViewSeller view = ViewSeller.convertIntoView(entity);
		compareViewAndEntity(view, entity);
	}

	/**
	 * Test method for {@link ViewSeller}'s getters and setters.
	 */
	@Test
	public final void testGetterSetter() {
		view = new ViewSeller();
		Enterprise enterprise = TestEnterprise.generateEnterpriseWithIdNull();
		view.setEnterprise(enterprise);
		assertEquals("Enterprise should be " + enterprise, enterprise, view.getEnterprise());
	}	

	/**
	 * Compare a view and an entity.
	 * @param view {@link ViewSeller}.
	 * @param entity {@link Seller}.
	 */
	private void compareViewAndEntity(ViewSeller view, Seller entity) {
		assertEquals(view.getId(), entity.getId());
		assertEquals(view.getFirstName(), entity.getFirstName());
		assertEquals(view.getLastName(), entity.getLastName());
		assertEquals(view.getUserName(), entity.getUserName());
		assertEquals(view.getPassword(), String.valueOf(entity.getPassword()));
		assertEquals(view.getEmail(), entity.getEmail());
		assertEquals(view.getEnterprise(), entity.getEnterprise());
	}

	/**
	 * Generate a dummy {@link ViewSeller} usable for tests.
	 * @return {@link ViewSeller}.
	 */
	static ViewSeller generateDummyViewSeller() {
		ViewSeller view = new ViewSeller();
		TestViewUser.setDummyValues(view);
		view.setEnterprise(TestEnterprise.generateEnterpriseWithIdNull());
		return view;
	}
}