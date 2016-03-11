package com.jomm.terroir.web;

import static com.jomm.terroir.util.Constants.ResourceBundleError.ENTITY_SHOULD_NOT_BE_NULL;
import static com.jomm.terroir.util.Constants.ResourceBundleError.EXCEPTION;
import static com.jomm.terroir.util.Constants.ResourceBundleError.ID_SHOULD_NOT_BE_NULL;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.DELETE_OK;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.DELETE_USER;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.UPDATE_OK;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.UPDATE_USER;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.ArgumentCaptor;
import org.primefaces.event.RowEditEvent;

import com.jomm.terroir.business.ServiceUser;
import com.jomm.terroir.business.model.AbstractUser;
import com.jomm.terroir.business.model.Customer;
import com.jomm.terroir.business.model.Seller;
import com.jomm.terroir.util.Resources;
import com.jomm.terroir.util.TestResources;
import com.jomm.terroir.util.exception.ExceptionService;
import com.jomm.terroir.util.exception.TestExceptionService;

/**
 * This class is a Junit test case testing the methods of {@link ViewUserList}.
 * It is annotated {@link RunWith} {@link Parameterized} to allow the test case to run with different parameters.
 * Here, the parameters are each child of {@link ViewUser}.
 * @author Maic
 */
@RunWith(Parameterized.class)
public class TestViewUserList {

	private ViewUserList view;
	private Class<AbstractUser> classUser;
	
	/**
	 * Constructor.
	 * Its parameter comes from all values from {@link TestViewUserList#childToTest()}.
	 * @param view the concrete child of {@link ViewUserList}.
	 * @param classUser the class of {@link AbstractUser}.
	 */
    public TestViewUserList(ViewUserList view, Class<AbstractUser> classUser) {
        this.view = view;
        this.classUser = classUser;
    }
    
	/**
	 * Test method for {@link ViewSellerList#onRowEdit(RowEditEvent)} with entity null.
	 * @throws Exception should not be thrown.
	 */
	@Test
	public final void testOnRowEditWithEntityNull() throws Exception {
		// initialization
		setInjections();
		// Simulate an exception thrown by service
		ExceptionService exception = TestExceptionService.createMockedExceptionEntityShouldNotBeNull();
		when(view.userService.update(any(classUser))).thenThrow(exception);
		RowEditEvent event = mock(RowEditEvent.class);
		when(event.getObject()).thenReturn(TestViewUser.generateDummyViewUser(classUser));
		// call to onRowEdit()
		view.onRowEdit(event);
		// verify Service.update() was called
		verify(view.userService).update(any(classUser));
		// verify FacesContext.addMessage() was called
		ArgumentCaptor<FacesMessage> messageCaptor = ArgumentCaptor.forClass(FacesMessage.class);
		verify(view.getFacesContext()).addMessage(any(), messageCaptor.capture());
        // retrieve the captured FacesMessage and check if it contains the expected values
		checkExceptionMessage(messageCaptor.getValue(), TestResources.getResourceBundleError(ENTITY_SHOULD_NOT_BE_NULL.getKey()));
	}
	
	/**
	 * Test method for {@link ViewSellerList#onRowEdit(RowEditEvent)} with id null.
	 * @throws Exception should not be thrown.
	 */
	@Test
	public final void testOnRowEditWithIdNull() throws Exception {
		// initialization
		setInjections();
		// Simulate an exception thrown by service
		ExceptionService exception = TestExceptionService.createMockedExceptionIdShouldNotBeNull();
		when(view.userService.update(any(classUser))).thenThrow(exception);
		RowEditEvent event = mock(RowEditEvent.class);
		when(event.getObject()).thenReturn(TestViewUser.generateDummyViewUser(classUser));
		// call to onRowEdit()
		view.onRowEdit(event);
		// verify Service.update() was called
		verify(view.userService).update(any(classUser));
		// verify FacesContext.addMessage() was called
		ArgumentCaptor<FacesMessage> messageCaptor = ArgumentCaptor.forClass(FacesMessage.class);
		verify(view.getFacesContext()).addMessage(any(), messageCaptor.capture());
        // retrieve the captured FacesMessage and check if it contains the expected values
		checkExceptionMessage(messageCaptor.getValue(), TestResources.getResourceBundleError(ID_SHOULD_NOT_BE_NULL.getKey()));
	}
	
	/**
	 * Test method for {@link ViewSellerList#onRowEdit(RowEditEvent)} with id not null.
	 * @throws Exception should not be thrown.
	 */
	@Test
	public final void testOnRowEditWithIdNotNull() throws Exception {
		// initialization
		setInjections();
		RowEditEvent event = mock(RowEditEvent.class);
		ViewUser viewUser = TestViewUser.generateDummyViewUser(classUser);
		when(event.getObject()).thenReturn(viewUser);
		// call to onRowEdit()
		view.onRowEdit(event);
		// verify Service.update() was called
		verify(view.userService).update(any(classUser));
		// verify FacesContext.addMessage() was called
		ArgumentCaptor<FacesMessage> messageCaptor = ArgumentCaptor.forClass(FacesMessage.class);
		verify(view.getFacesContext()).addMessage(any(), messageCaptor.capture());
        // retrieve the captured FacesMessage and check if it contains the expected values
		checkValidationMessage(messageCaptor.getValue(), TestResources.getResourceBundleMessage(UPDATE_OK.getKey()), 
				TestResources.getResourceBundleMessage(UPDATE_USER.getKey()));
	}
	
	/**
	 * Test method for {@link ViewSellerList#delete()} with current customer null.
	 * @throws Exception should not be thrown.
	 */
	@Test
	public final void testDeleteWithCurrentUserNull() throws Exception {
		// initialization
		setInjections();
		view.setCurrentUser(null);
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
	public final void testDeleteWithEntityNull() throws Exception {
		// initialization
		setInjections();
		view.setCurrentUser(TestViewUser.generateDummyViewUser(classUser));
		// Simulate an exception thrown by service
		ExceptionService exception = TestExceptionService.createMockedExceptionEntityShouldNotBeNull();
		doThrow(exception).when(view.userService).delete(any(classUser));
		// call to delete()
		view.delete();
		// verify Service.delete() was called
		verify(view.userService).delete(any(classUser));
		// verify FacesContext.addMessage() was called
		ArgumentCaptor<FacesMessage> messageCaptor = ArgumentCaptor.forClass(FacesMessage.class);
		verify(view.getFacesContext()).addMessage(any(), messageCaptor.capture());
        // retrieve the captured FacesMessage and check if it contains the expected values
		checkExceptionMessage(messageCaptor.getValue(), TestResources.getResourceBundleError(ENTITY_SHOULD_NOT_BE_NULL.getKey()));
	}
	
	/**
	 * Test method for {@link ViewSellerList#delete()} with entity's id null.
	 * @throws Exception should not be thrown.
	 */
	@Test
	public final void testDeleteWithIdNull() throws Exception {
		// initialization
		setInjections();
		view.setCurrentUser(TestViewUser.generateDummyViewUser(classUser));
		// Simulate an exception thrown by service
		ExceptionService exception = TestExceptionService.createMockedExceptionIdShouldNotBeNull();
		doThrow(exception).when(view.userService).delete(any(classUser));
		// call to delete()
		view.delete();
		// verify Service.delete() was called
		verify(view.userService).delete(any(classUser));
		// verify FacesContext.addMessage() was called
		ArgumentCaptor<FacesMessage> messageCaptor = ArgumentCaptor.forClass(FacesMessage.class);
		verify(view.getFacesContext()).addMessage(any(), messageCaptor.capture());
        // retrieve the captured FacesMessage and check if it contains the expected values
		checkExceptionMessage(messageCaptor.getValue(), TestResources.getResourceBundleError(ID_SHOULD_NOT_BE_NULL.getKey()));
	}
	
	/**
	 * Test method for {@link ViewSellerList#delete()} with entity's id not null.
	 * @throws Exception should not be thrown.
	 */
	@Test
	public final void testDeleteWithIdNotNull() throws Exception {
		// initialization
		setInjections();
		ViewUser viewUser = TestViewUser.generateDummyViewUser(classUser);
		view.setCurrentUser(viewUser);
		// call to delete()
		view.delete();
		// verify Service.delete() was called
		verify(view.userService).delete(any(classUser));
		// verify FacesContext.addMessage() was called
		ArgumentCaptor<FacesMessage> messageCaptor = ArgumentCaptor.forClass(FacesMessage.class);
		verify(view.getFacesContext()).addMessage(any(), messageCaptor.capture());
        // retrieve the captured FacesMessage and check if it contains the expected values
		checkValidationMessage(messageCaptor.getValue(), TestResources.getResourceBundleMessage(DELETE_OK.getKey()), 
				TestResources.getResourceBundleMessage(DELETE_USER.getKey()));
	}

	/**
	 * Test method for {@link ViewSellerList}'s getters and setters.
	 */
	@Test
	public final void testGetterSetter() {		
		// HtmlDataTable
		HtmlDataTable dataTable = new HtmlDataTable();
		view.setDataTable(dataTable);
		assertEquals("HtmlDataTable should be " + dataTable, dataTable, view.getDataTable());
	}
	
	/**
	 * Set mocked {@link FacesContext}, mocked {@link ServiceUser},
	 * and a dummy {@link java.util.logging.Logger} into view.
	 * Retrieve the {@link java.util.ResourceBundle} Message from {@link Resources}.
	 */
	private void setInjections() {
		view.setFacesContext(mock(FacesContext.class));
		view.userService = mock(ServiceUser.class);
		view.setResourceBundleMessage(Resources.getResourceBundleMessage());
		view.setResourceBundleError(Resources.getResourceBundleError());
		view.logger = TestResources.createLogger(view.getClass());
	}
	
	/**
	 * Check if the thrown {@link FacesMessage} has correct values.
	 * @param message the {@link FacesMessage}.
	 * @param detail String the message's detail.
	 */
	private void checkExceptionMessage(FacesMessage message, String detail) {
		// check severity
		assertEquals(FacesMessage.SEVERITY_ERROR, message.getSeverity());
		// check summary
        assertEquals(TestResources.getResourceBundleError(EXCEPTION.getKey()), message.getSummary());
        // check detail
		assertTrue(message.getDetail().contains(detail));
	}
	
	/**
	 * Check if the thrown {@link FacesMessage} has correct values.
	 * @param message the {@link FacesMessage}.
	 * @param summary String the message's summary.
	 * @param detail String the message's detail.
	 */
	private void checkValidationMessage(FacesMessage message, String summary, String detail) {
		// check severity
		assertEquals(FacesMessage.SEVERITY_INFO, message.getSeverity());
		// check summary
        assertEquals(summary, message.getSummary());
        // check detail
        String[] detailParts = detail.replace("''", "'").split(Pattern.quote(" {0} "));
        assertTrue(message.getDetail().startsWith(detailParts[0]));
        assertTrue(message.getDetail().endsWith(detailParts[1]));
	}
	
	/**
	 * Reference a list of all {@link ViewUserList}'s concrete children, 
	 * and the class of the associated child of {@link AbstractUser},
	 * to be used as parameters on constructor.
	 * Each iteration will be tested with all test methods.
	 * @return {@code Iterable<Object[]>} with both parameters.
	 */
	@Parameters
	public static Iterable<Object[]> childToTest() {
		return Arrays.asList(new Object[][] {
			{new ViewCustomerList(), Customer.class},
			{new ViewSellerList(), Seller.class}
			}
		);
	}
}