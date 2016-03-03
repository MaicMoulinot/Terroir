package com.jomm.terroir.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.LinkedList;

import javax.faces.component.html.HtmlDataTable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jomm.terroir.business.ServiceUser;

/**
 * This class is a Junit test case testing {@link ViewSellerList}.
 * @author Maic
 */
public class TestViewSellerList {
	
	private ViewSellerList view;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		view = new ViewSellerList();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		view = null; // Available for Garbage Collector
	}

	/**
	 * Test method for {@link ViewSellerList#init()}.
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
	 * Test method for {@link ViewSellerList}'s getters and setters.
	 */
	@Test
	public final void testGetterSetter() {		
		// ViewSeller
		ViewSeller current = TestViewSeller.generateDummyViewSeller();
		view.setCurrentUser(current);
		assertEquals("ViewSeller should be " + current, current, view.getCurrentUser());
		
		// HtmlDataTable
		HtmlDataTable dataTable = new HtmlDataTable();
		view.setDataTable(dataTable);
		assertEquals("HtmlDataTable should be " + dataTable, dataTable, view.getDataTable());
		
		// ListSellers
		LinkedList<ViewSeller> listSellers = new LinkedList<>();
		view.setListSellers(listSellers);
		assertEquals("ListSellers should be " + listSellers, listSellers, view.getListSellers());
	}
}