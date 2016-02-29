package com.jomm.terroir.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.MessageFormat;
import java.util.LinkedList;

import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.primefaces.event.RowEditEvent;

import com.jomm.terroir.business.ServiceUser;
import com.jomm.terroir.business.model.AbstractUser;
import com.jomm.terroir.business.model.Seller;
import com.jomm.terroir.util.Resources;

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
		setInjections();
		view.init();
		// verify Service.getAllSellers() was called
		verify(view.userService).getAllSellers();
		assertNotNull(view.getListSellers());
	}
	
	/**
	 * Test method for {@link ViewSellerList#onRowEdit(RowEditEvent)} with entity null.
	 */
	@Test
	public final void testOnRowEditWithNullPointerException() {
		// initialization
		setInjections();
		when(view.userService.update(any(Seller.class))).thenThrow(new NullPointerException());
		RowEditEvent event = mock(RowEditEvent.class);
		when(event.getObject()).thenReturn(new ViewSeller());
		// call to onRowEdit()
		view.onRowEdit(event);
		// verify Service.update() was called
		verify(view.userService).update(any(Seller.class));
		// verify FacesContext.addMessage() was called
		ArgumentCaptor<FacesMessage> messageCaptor = ArgumentCaptor.forClass(FacesMessage.class);
		verify(view.facesContext).addMessage(any(), messageCaptor.capture());
        // retrieve the captured FacesMessage and check if it contains the expected values
		FacesMessage message = messageCaptor.getValue();
		assertEquals(FacesMessage.SEVERITY_ERROR, message.getSeverity());
        assertEquals(view.resourceError.getString(ViewSellerList.USER_NULL), message.getSummary());
	}
	
	/**
	 * Test method for {@link ViewSellerList#onRowEdit(RowEditEvent)} with id null.
	 */
	@Test
	public final void testOnRowEditWithIllegalArgumentException() {
		// initialization
		setInjections();
		when(view.userService.update(any(Seller.class))).thenThrow(new IllegalArgumentException());
		RowEditEvent event = mock(RowEditEvent.class);
		when(event.getObject()).thenReturn(new ViewSeller());
		// call to onRowEdit()
		view.onRowEdit(event);
		// verify Service.update() was called
		verify(view.userService).update(any(Seller.class));
		// verify FacesContext.addMessage() was called
		ArgumentCaptor<FacesMessage> messageCaptor = ArgumentCaptor.forClass(FacesMessage.class);
		verify(view.facesContext).addMessage(any(), messageCaptor.capture());
        // retrieve the captured FacesMessage and check if it contains the expected values
		FacesMessage message = messageCaptor.getValue();
		assertEquals(FacesMessage.SEVERITY_ERROR, message.getSeverity());
        assertEquals(view.resourceError.getString(ViewSellerList.ID_NULL), message.getSummary());
	}
	
	/**
	 * Test method for {@link ViewSellerList#onRowEdit(RowEditEvent)} with id not null.
	 */
	@Test
	public final void testOnRowEditWithEntityIdNull() {
		// initialization
		setInjections();
		RowEditEvent event = mock(RowEditEvent.class);
		ViewSeller viewSeller = TestViewSeller.generateDummyViewSeller();
		when(event.getObject()).thenReturn(viewSeller);
		// call to onRowEdit()
		view.onRowEdit(event);
		// verify Service.update() was called
		verify(view.userService).update(any(Seller.class));
		// verify FacesContext.addMessage() was called
		ArgumentCaptor<FacesMessage> messageCaptor = ArgumentCaptor.forClass(FacesMessage.class);
		verify(view.facesContext).addMessage(any(), messageCaptor.capture());
        // retrieve the captured FacesMessage and check if it contains the expected values
		FacesMessage message = messageCaptor.getValue();
		assertEquals(FacesMessage.SEVERITY_INFO, message.getSeverity());
        assertEquals(view.resourceMessage.getString(ViewSellerList.UPDATE_OK), message.getSummary());
        Object[] argument = {viewSeller.getUserName()};
        assertEquals(MessageFormat.format(view.resourceMessage.getString(ViewSellerList.UPDATE_USER), argument), 
        		message.getDetail());
	}
	
	/**
	 * Test method for {@link ViewSellerList#delete()} with current seller null.
	 */
	@Test
	public final void testDeleteWithCurrentSellerNull() {
		// initialization
		setInjections();
		view.setCurrentSeller(null);
		// call to delete()
		view.delete();
		// verify Service.delete() was not called
		verify(view.userService, never()).delete(any(Seller.class));
	}
	
	/**
	 * Test method for {@link ViewSellerList#delete()} with entity null.
	 */
	@Test
	public final void testDeleteWithNullPointerException() {
		// initialization
		setInjections();
		view.setCurrentSeller(TestViewSeller.generateDummyViewSeller());
		doThrow(new NullPointerException()).when(view.userService).delete(any(AbstractUser.class));
		// call to delete()
		view.delete();
		// verify Service.delete() was called
		verify(view.userService).delete(any(Seller.class));
		// verify FacesContext.addMessage() was called
		ArgumentCaptor<FacesMessage> messageCaptor = ArgumentCaptor.forClass(FacesMessage.class);
		verify(view.facesContext).addMessage(any(), messageCaptor.capture());
        // retrieve the captured FacesMessage and check if it contains the expected values
		FacesMessage message = messageCaptor.getValue();
		assertEquals(FacesMessage.SEVERITY_ERROR, message.getSeverity());
        assertEquals(view.resourceError.getString(ViewSellerList.USER_NULL), message.getSummary());
	}
	
	/**
	 * Test method for {@link ViewSellerList#delete()} with entity's id null.
	 */
	@Test
	public final void testDeleteWithIllegalArgumentException() {
		// initialization
		setInjections();
		view.setCurrentSeller(TestViewSeller.generateDummyViewSeller());
		doThrow(new IllegalArgumentException()).when(view.userService).delete(any(AbstractUser.class));
		// call to delete()
		view.delete();
		// verify Service.delete() was called
		verify(view.userService).delete(any(Seller.class));
		// verify FacesContext.addMessage() was called
		ArgumentCaptor<FacesMessage> messageCaptor = ArgumentCaptor.forClass(FacesMessage.class);
		verify(view.facesContext).addMessage(any(), messageCaptor.capture());
        // retrieve the captured FacesMessage and check if it contains the expected values
		FacesMessage message = messageCaptor.getValue();
		assertEquals(FacesMessage.SEVERITY_ERROR, message.getSeverity());
        assertEquals(view.resourceError.getString(ViewSellerList.ID_NULL), message.getSummary());
	}
	
	/**
	 * Test method for {@link ViewSellerList#delete()} with entity's id not null.
	 */
	@Test
	public final void testDeleteWithEntityIdNotNull() {
		// initialization
		setInjections();
		ViewSeller viewSeller = TestViewSeller.generateDummyViewSeller();
		view.setCurrentSeller(viewSeller);
		// call to delete()
		view.delete();
		// verify Service.delete() was called
		verify(view.userService).delete(any(Seller.class));
		// verify FacesContext.addMessage() was called
		ArgumentCaptor<FacesMessage> messageCaptor = ArgumentCaptor.forClass(FacesMessage.class);
		verify(view.facesContext).addMessage(any(), messageCaptor.capture());
        // retrieve the captured FacesMessage and check if it contains the expected values
		FacesMessage message = messageCaptor.getValue();
		assertEquals(FacesMessage.SEVERITY_INFO, message.getSeverity());
        assertEquals(view.resourceMessage.getString(ViewSellerList.DELETE_OK), message.getSummary());
        Object[] argument = {viewSeller.getUserName()};
        assertEquals(MessageFormat.format(view.resourceMessage.getString(ViewSellerList.DELETE_USER), argument), 
        		message.getDetail());
	}
	
	/**
	 * Test method for {@link ViewSellerList}'s getters and setters.
	 */
	@Test
	public final void testGetterSetter() {		
		// ViewSeller
		ViewSeller current = TestViewSeller.generateDummyViewSeller();
		view.setCurrentSeller(current);
		assertEquals("ViewSeller should be " + current, current, view.getCurrentSeller());
		
		// HtmlDataTable
		HtmlDataTable dataTable = new HtmlDataTable();
		view.setDataTable(dataTable);
		assertEquals("HtmlDataTable should be " + dataTable, dataTable, view.getDataTable());
		
		// ListSellers
		LinkedList<ViewSeller> listSellers = new LinkedList<>();
		view.setListSellers(listSellers);
		assertEquals("ListSellers should be " + listSellers, listSellers, view.getListSellers());
	}
	
	/**
	 * Set mocked {@link javax.faces.context.FacesContext}, and mocked {@link ServiceUser} into view.
	 * Retrieve the {@link java.util.ResourceBundle}s from {@link Resources}.
	 */
	private void setInjections() {
		view.facesContext = mock(FacesContext.class);
		view.userService = mock(ServiceUser.class);
		view.resourceError = Resources.getResourceBundleError();
		view.resourceMessage = Resources.getResourceBundleMessage();
	}
}