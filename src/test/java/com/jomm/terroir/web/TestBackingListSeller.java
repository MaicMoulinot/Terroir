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
import com.jomm.terroir.business.model.Seller;
import com.jomm.terroir.business.model.TestSeller;

/**
 * This class is a Junit test case testing {@link BackingListSeller}.
 * @author Maic
 */
public class TestBackingListSeller {
	
	// Variables //-----------------------------------------------
	private BackingListSeller view;

	// Test methods //--------------------------------------------
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		view = new BackingListSeller();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		view = null; // Available for Garbage Collector
	}

	/**
	 * Test method for {@link BackingListSeller#init()}.
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
	 * Test method for {@link BackingListSeller}'s getters and setters.
	 */
	@Test
	public final void testGetterSetter() {		
		// ViewSeller
		Seller current = TestSeller.generateSellerWithIdNull();
		view.setCurrentUser(current);
		assertEquals("ViewSeller should be " + current, current, view.getCurrentUser());
		
		// ListSellers
		LinkedList<Seller> listSellers = new LinkedList<>();
		view.setListSellers(listSellers);
		assertEquals("ListSellers should be " + listSellers, listSellers, view.getListSellers());
	}
}