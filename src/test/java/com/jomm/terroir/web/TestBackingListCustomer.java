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
import com.jomm.terroir.business.model.Customer;
import com.jomm.terroir.business.model.TestCustomer;

/**
 * This class is a Junit test case testing {@link BackingListCustomer}.
 * @author Maic
 */
public class TestBackingListCustomer {
	
	// Variables //-----------------------------------------------
	private BackingListCustomer view;

	// Test methods //--------------------------------------------
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		view = new BackingListCustomer();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		view = null; // Available for Garbage Collector
	}

	/**
	 * Test method for {@link BackingListCustomer#init()}.
	 */
	@Test
	public final void testInit() {
		assertNull(view.getListCustomers());
		view.userService = mock(ServiceUser.class);
		view.init();
		// verify Service.getAllCustomers() was called
		verify(view.userService).getAllCustomers();
		assertNotNull(view.getListCustomers());
	}
	
	/**
	 * Test method for {@link BackingListCustomer}'s getters and setters.
	 */
	@Test
	public final void testGetterSetter() {		
		// ViewCustomer
		Customer current = TestCustomer.generateCustomerWithIdNull();
		view.setCurrentUser(current);
		assertEquals("ViewCustomer should be " + current, current, view.getCurrentUser());
		
		// ListCustomers
		LinkedList<Customer> listCustomers = new LinkedList<>();
		view.setListCustomers(listCustomers);
		assertEquals("ListCustomers should be " + listCustomers, listCustomers, view.getListCustomers());
	}
}