package com.jomm.terroir.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jomm.terroir.business.ServiceUser;

/**
 * This class is a Junit test case testing {@link BeanListSeller}.
 * @author Maic
 */
public class TestBeanListSeller {
	
	private BeanListSeller view;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		view = new BeanListSeller();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		view = null; // Available for Garbage Collector
	}

	/**
	 * Test method for {@link BeanListSeller#init()}.
	 */
	@Test
	public final void testInit() {
		assertNull(view.getListSellers());
		view.userService = mock(ServiceUser.class);
		view.init();
		// verify Service.getAllSellers() was called
		verify(view.userService).getAllSellers();
		assertNotNull(view.getListSellers());
	}
	
	/**
	 * Test method for {@link BeanListSeller}'s getters and setters.
	 */
	@Test
	public final void testGetterSetter() {		
		// ViewSeller
		BeanRegistrationSeller current = TestBeanRegistrationSeller.generateDummyViewSeller();
		view.setCurrentUser(current);
		assertEquals("ViewSeller should be " + current, current, view.getCurrentUser());
		
		// ListSellers
		LinkedList<BeanRegistrationSeller> listSellers = new LinkedList<>();
		view.setListSellers(listSellers);
		assertEquals("ListSellers should be " + listSellers, listSellers, view.getListSellers());
	}
}