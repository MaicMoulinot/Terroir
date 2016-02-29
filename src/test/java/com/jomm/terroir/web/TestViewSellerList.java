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
import com.jomm.terroir.util.Constants;
import com.jomm.terroir.util.Resources;
import com.jomm.terroir.util.exception.ExceptionInvalidId;
import com.jomm.terroir.util.exception.ExceptionNullEntity;

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
	 * @throws Exception should not be thrown.
	 */
	@Test
	public final void testOnRowEditWithExceptionNullEntity() throws Exception {
		// initialization
		setInjections();
		when(view.userService.update(any(Seller.class))).thenThrow(new ExceptionNullEntity());
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
        assertEquals(view.resourceError.getString(Constants.USER_SHOULD_NOT_BE_NULL), message.getSummary());
	}
	
	/**
	 * Test method for {@link ViewSellerList#onRowEdit(RowEditEvent)} with id null.
	 * @throws Exception should not be thrown.
	 */
	@Test
	public final void testOnRowEditWithExceptionInvalidId() throws Exception {
		// initialization
		setInjections();
		when(view.userService.update(any(Seller.class))).thenThrow(new ExceptionInvalidId(false));
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
        assertEquals(view.resourceError.getString(Constants.ID_SHOULD_NOT_BE_NULL), message.getSummary());
	}
	
	/**
	 * Test method for {@link ViewSellerList#onRowEdit(RowEditEvent)} with id not null.
	 * @throws Exception should not be thrown.
	 */
	@Test
	public final void testOnRowEditWithEntityIdNull() throws Exception {
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
        assertEquals(view.resourceMessage.getString(Constants.UPDATE_OK), message.getSummary());
        Object[] argument = {viewSeller.getUserName()};
        assertEquals(MessageFormat.format(view.resourceMessage.getString(Constants.UPDATE_USER), argument), 
        		message.getDetail());
	}
	
	/**
	 * Test method for {@link ViewSellerList#delete()} with current seller null.
	 * @throws Exception should not be thrown.
	 */
	@Test
	public final void testDeleteWithCurrentSellerNull() throws Exception {
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
	 * @throws Exception should not be thrown.
	 */
	@Test
	public final void testDeleteWithExceptionNullEntity() throws Exception {
		// initialization
		setInjections();
		view.setCurrentSeller(TestViewSeller.generateDummyViewSeller());
		doThrow(new ExceptionNullEntity()).when(view.userService).delete(any(AbstractUser.class));
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
        assertEquals(view.resourceError.getString(Constants.USER_SHOULD_NOT_BE_NULL), message.getSummary());
	}
	
	/**
	 * Test method for {@link ViewSellerList#delete()} with entity's id null.
	 * @throws Exception should not be thrown.
	 */
	@Test
	public final void testDeleteWithExceptionInvalidId() throws Exception {
		// initialization
		setInjections();
		view.setCurrentSeller(TestViewSeller.generateDummyViewSeller());
		doThrow(new ExceptionInvalidId(false)).when(view.userService).delete(any(AbstractUser.class));
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
        assertEquals(view.resourceError.getString(Constants.ID_SHOULD_NOT_BE_NULL), message.getSummary());
	}
	
	/**
	 * Test method for {@link ViewSellerList#delete()} with entity's id not null.
	 * @throws Exception should not be thrown.
	 */
	@Test
	public final void testDeleteWithEntityIdNotNull() throws Exception {
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
        assertEquals(view.resourceMessage.getString(Constants.DELETE_OK), message.getSummary());
        Object[] argument = {viewSeller.getUserName()};
        assertEquals(MessageFormat.format(view.resourceMessage.getString(Constants.DELETE_USER), argument), 
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