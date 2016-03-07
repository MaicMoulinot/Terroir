package com.jomm.terroir.web;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
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
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		view = generateDummyViewSeller();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		view = null; // Available for Garbage Collector
	}

	/**
	 * Test method for {@link ViewSeller#convertIntoEntity()}.
	 */
	@Test
	public final void testConvertIntoEntity() {
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
		// Enterprise
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