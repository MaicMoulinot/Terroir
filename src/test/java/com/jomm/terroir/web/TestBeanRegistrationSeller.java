package com.jomm.terroir.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.jomm.terroir.business.model.Enterprise;
import com.jomm.terroir.business.model.Seller;
import com.jomm.terroir.business.model.TestEnterprise;

/**
 * This class is a Junit test case testing {@link BeanRegistrationSeller}.
 * @author Maic
 */
public class TestBeanRegistrationSeller {

	private BeanRegistrationSeller view;
	
	/**
	 * Test method for {@link BeanRegistrationSeller#init()}.
	 */
	@Test
	public final void testInit() {
		view = new BeanRegistrationSeller();
		assertNull(view.getEnterprise());
		view.init();
		assertNotNull(view.getEnterprise());
	}

	/**
	 * Test method for {@link BeanRegistrationSeller#convertIntoEntity()}.
	 */
	@Test
	public final void testConvertIntoEntity() {
		view = generateDummyViewSeller();
		view.setId((long) 3333);
		Seller entity = view.convertIntoEntity();
		compareViewAndEntity(view, entity);
	}

	/**
	 * Test method for {@link BeanRegistrationSeller}'s getters and setters.
	 */
	@Test
	public final void testGetterSetter() {
		view = new BeanRegistrationSeller();
		Enterprise enterprise = TestEnterprise.generateEnterpriseWithIdNull();
		view.setEnterprise(enterprise);
		assertEquals("Enterprise should be " + enterprise, enterprise, view.getEnterprise());
	}	

	/**
	 * Compare a view and an entity.
	 * @param view {@link BeanRegistrationSeller}.
	 * @param entity {@link Seller}.
	 */
	private void compareViewAndEntity(BeanRegistrationSeller view, Seller entity) {
		assertEquals(view.getId(), entity.getId());
		assertEquals(view.getFirstName(), entity.getFirstName());
		assertEquals(view.getLastName(), entity.getLastName());
		assertEquals(view.getUserName(), entity.getUserName());
		assertEquals(view.getPassword(), String.valueOf(entity.getPassword()));
		assertEquals(view.getEmail(), entity.getEmail());
		assertEquals(view.getEnterprise(), entity.getEnterprise());
	}

	/**
	 * Generate a dummy {@link BeanRegistrationSeller} usable for tests.
	 * @return {@link BeanRegistrationSeller}.
	 */
	static BeanRegistrationSeller generateDummyViewSeller() {
		BeanRegistrationSeller view = new BeanRegistrationSeller();
		TestBeanRegistrationUser.setDummyValues(view);
		view.setEnterprise(TestEnterprise.generateEnterpriseWithIdNull());
		return view;
	}
}