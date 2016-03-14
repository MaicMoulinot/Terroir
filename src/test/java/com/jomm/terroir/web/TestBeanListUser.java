package com.jomm.terroir.web;

import static com.jomm.terroir.util.Constants.ResourceBundleError.EXCEPTION;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.DELETE_OK;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.DELETE_USER;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.UPDATE_OK;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.UPDATE_USER;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlDataTable;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.primefaces.event.RowEditEvent;

import com.jomm.terroir.business.ServiceUser;
import com.jomm.terroir.business.model.AbstractUser;
import com.jomm.terroir.business.model.Customer;
import com.jomm.terroir.business.model.Seller;
import com.jomm.terroir.util.TestResources;
import com.jomm.terroir.util.exception.ExceptionService;
import com.jomm.terroir.util.exception.TestExceptionService;

/**
 * This class is a Junit test case testing the methods of {@link BeanListUser}.
 * It is annotated {@link RunWith} {@link Parameterized} to allow the test case to run with different parameters.
 * Here, the parameters are each child of {@link BeanListUser} 
 * with its associated concrete child of {@link AbstractUser}.
 * @author Maic
 */
@RunWith(Parameterized.class)
public class TestBeanListUser {

	private BeanListUser view;
	private AbstractUser user;
	
	/**
	 * Constructor.
	 * Its parameter comes from all values from {@link TestBeanListUser#childToTest()}.
	 * @param view the concrete child of {@link BeanListUser}.
	 * @param user the concrete child of {@link AbstractUser}.
	 */
    public TestBeanListUser(BeanListUser view, AbstractUser user) {
        this.view = view;
        this.user = user;
    }
    
	/**
	 * Test method for {@link BeanListSeller#onRowEdit(RowEditEvent)} with entity null.
	 * @throws Exception should not be thrown.
	 */
	@Test
	public final void testOnRowEditWithEntityNull() throws Exception {
		// initialization
		setInjections();
		// Simulate an exception thrown by service
		ExceptionService exception = TestExceptionService.createMockedExceptionEntityShouldNotBeNull();
		when(view.userService.update(any(user.getClass()))).thenThrow(exception);
		RowEditEvent event = mock(RowEditEvent.class);
		BeanRegistrationUser currentUser = TestBeanRegistrationUser.generateDummyViewUser(user);
		when(event.getObject()).thenReturn(currentUser);
		// call to onRowEdit()
		view.onRowEdit(event);
		// verify Service.update() was called
		verify(view.userService).update(any(user.getClass()));
		// check if a FacesMessage was correctly thrown
		TestAbstractBean.checkMessageWithPlainDetail(view, null, FacesMessage.SEVERITY_ERROR, 
				TestResources.getValueFromResourceBundle(EXCEPTION), 
				view.generateExceptionMessage(exception, currentUser.getId(), user));
	}
	
	/**
	 * Test method for {@link BeanListSeller#onRowEdit(RowEditEvent)} with id null.
	 * @throws Exception should not be thrown.
	 */
	@Test
	public final void testOnRowEditWithIdNull() throws Exception {
		// initialization
		setInjections();
		// Simulate an exception thrown by service
		ExceptionService exception = TestExceptionService.createMockedExceptionIdShouldNotBeNull();
		when(view.userService.update(any(user.getClass()))).thenThrow(exception);
		RowEditEvent event = mock(RowEditEvent.class);
		BeanRegistrationUser currentUser = TestBeanRegistrationUser.generateDummyViewUser(user);
		when(event.getObject()).thenReturn(currentUser);
		// call to onRowEdit()
		view.onRowEdit(event);
		// verify Service.update() was called
		verify(view.userService).update(any(user.getClass()));
		// check if a FacesMessage was correctly thrown
		TestAbstractBean.checkMessageWithPlainDetail(view, null, FacesMessage.SEVERITY_ERROR, 
				TestResources.getValueFromResourceBundle(EXCEPTION), 
				view.generateExceptionMessage(exception, currentUser.getId(), user));
	}
	
	/**
	 * Test method for {@link BeanListSeller#onRowEdit(RowEditEvent)} with id not null.
	 * @throws Exception should not be thrown.
	 */
	@Test
	public final void testOnRowEditWithIdNotNull() throws Exception {
		// initialization
		setInjections();
		RowEditEvent event = mock(RowEditEvent.class);
		BeanRegistrationUser viewUser = TestBeanRegistrationUser.generateDummyViewUser(user);
		when(event.getObject()).thenReturn(viewUser);
		// call to onRowEdit()
		view.onRowEdit(event);
		// verify Service.update() was called
		verify(view.userService).update(any(user.getClass()));
		// check if a FacesMessage was correctly thrown
		TestAbstractBean.checkMessageWithParametrizedDetail(view, null, FacesMessage.SEVERITY_INFO, 
				TestResources.getValueFromResourceBundle(UPDATE_OK), 
				TestResources.getValueFromResourceBundle(UPDATE_USER));
	}
	
	/**
	 * Test method for {@link BeanListSeller#delete()} with current customer null.
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
		verify(view.userService, never()).delete(any(user.getClass()));
	}
	
	/**
	 * Test method for {@link BeanListSeller#delete()} with entity null.
	 * @throws Exception should not be thrown.
	 */
	@Test
	public final void testDeleteWithEntityNull() throws Exception {
		// initialization
		setInjections();
		view.setCurrentUser(TestBeanRegistrationUser.generateDummyViewUser(user));
		// Simulate an exception thrown by service
		ExceptionService exception = TestExceptionService.createMockedExceptionEntityShouldNotBeNull();
		doThrow(exception).when(view.userService).delete(any(user.getClass()));
		// call to delete()
		view.delete();
		// verify Service.delete() was called
		verify(view.userService).delete(any(user.getClass()));
		// check if a FacesMessage was correctly thrown
		TestAbstractBean.checkMessageWithPlainDetail(view, null, FacesMessage.SEVERITY_ERROR, 
				TestResources.getValueFromResourceBundle(EXCEPTION), 
				view.generateExceptionMessage(exception, view.getCurrentUser().getId(), user));
	}
	
	/**
	 * Test method for {@link BeanListSeller#delete()} with entity's id null.
	 * @throws Exception should not be thrown.
	 */
	@Test
	public final void testDeleteWithIdNull() throws Exception {
		// initialization
		setInjections();
		view.setCurrentUser(TestBeanRegistrationUser.generateDummyViewUser(user));
		// Simulate an exception thrown by service
		ExceptionService exception = TestExceptionService.createMockedExceptionIdShouldNotBeNull();
		doThrow(exception).when(view.userService).delete(any(user.getClass()));
		// call to delete()
		view.delete();
		// verify Service.delete() was called
		verify(view.userService).delete(any(user.getClass()));
		// check if a FacesMessage was correctly thrown
		TestAbstractBean.checkMessageWithPlainDetail(view, null, FacesMessage.SEVERITY_ERROR, 
				TestResources.getValueFromResourceBundle(EXCEPTION), 
				view.generateExceptionMessage(exception, view.getCurrentUser().getId(), user));
	}
	
	/**
	 * Test method for {@link BeanListSeller#delete()} with entity's id not null.
	 * @throws Exception should not be thrown.
	 */
	@Test
	public final void testDeleteWithIdNotNull() throws Exception {
		// initialization
		setInjections();
		view.setCurrentUser(TestBeanRegistrationUser.generateDummyViewUser(user));
		// call to delete()
		view.delete();
		// verify Service.delete() was called
		verify(view.userService).delete(any(user.getClass()));
		// check if a FacesMessage was correctly thrown
		TestAbstractBean.checkMessageWithParametrizedDetail(view, null, FacesMessage.SEVERITY_INFO, 
				TestResources.getValueFromResourceBundle(DELETE_OK), 
				TestResources.getValueFromResourceBundle(DELETE_USER));
	}

	/**
	 * Test method for {@link BeanListSeller}'s getters and setters.
	 */
	@Test
	public final void testGetterSetter() {		
		// HtmlDataTable
		HtmlDataTable dataTable = new HtmlDataTable();
		view.setDataTable(dataTable);
		assertEquals("HtmlDataTable should be " + dataTable, dataTable, view.getDataTable());
	}
	
	/**
	 * Set mocked {@link ServiceUser}, and a dummy {@link java.util.logging.Logger} into view.
	 * Call {@link TestAbstractBean#setInjections(AbstractView)}.
	 */
	private void setInjections() {
		TestAbstractBean.setInjections(view);
		view.userService = mock(ServiceUser.class);
		view.logger = TestResources.createLogger(view.getClass());
	}
	
	/**
	 * Reference a list of all {@link BeanListUser}'s concrete children, 
	 * and the associated concrete child of {@link AbstractUser},
	 * to be used as parameters on constructor.
	 * Each iteration will be tested with all test methods.
	 * @return {@code Iterable<Object[]>} with both parameters.
	 */
	@Parameters
	public static Iterable<Object[]> childToTest() {
		return Arrays.asList(new Object[][] {
			{new BeanListCustomer(), new Customer()},
			{new BeanListSeller(), new Seller()}
			}
		);
	}
}