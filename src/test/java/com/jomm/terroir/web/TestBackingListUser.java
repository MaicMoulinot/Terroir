package com.jomm.terroir.web;

import static com.jomm.terroir.util.Constants.ResourceBundleError.EXCEPTION;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.DELETE;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.DELETE_OK;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.UPDATE;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.UPDATE_OK;
import static com.jomm.terroir.util.Resources.getValueFromKey;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import javax.faces.application.FacesMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.primefaces.event.RowEditEvent;
import org.w3c.dom.views.AbstractView;

import com.jomm.terroir.business.ServiceUser;
import com.jomm.terroir.business.model.AbstractUser;
import com.jomm.terroir.business.model.Administrator;
import com.jomm.terroir.business.model.Customer;
import com.jomm.terroir.business.model.Seller;
import com.jomm.terroir.util.TestResources;
import com.jomm.terroir.util.Constants.Entity;
import com.jomm.terroir.util.exception.ExceptionService;
import com.jomm.terroir.util.exception.TestExceptionService;

/**
 * This class is a Junit test case testing the methods of {@link BackingListUser}.
 * It is annotated {@link RunWith} {@link Parameterized} to allow the test case to run with different parameters.
 * Here, the parameters are each child of {@link BackingListUser} 
 * with its associated concrete child of {@link AbstractUser}.
 * @author Maic
 */
@RunWith(Parameterized.class)
public class TestBackingListUser {
	
	// Variables //-----------------------------------------------
	private BackingListUser view;
	private AbstractUser user;
	
	// Constructors //--------------------------------------------
	/**
	 * Constructor.
	 * Its parameter comes from all values from {@link TestBackingListUser#childToTest()}.
	 * @param view the concrete child of {@link BackingListUser}.
	 * @param user the concrete child of {@link AbstractUser}.
	 */
    public TestBackingListUser(BackingListUser view, AbstractUser user) {
        this.view = view;
        this.user = user;
    }
    
    // Test methods //--------------------------------------------
	/**
	 * Test method for {@link BackingListSeller#onRowEdit(RowEditEvent)} with entity null.
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
		when(event.getObject()).thenReturn(user);
		// call to onRowEdit()
		view.onRowEdit(event);
		// verify Service.update() was called
		verify(view.userService).update(any(user.getClass()));
		// check if a FacesMessage was correctly thrown
		TestBackingBean.checkMessageWithPlainDetail(view, null, FacesMessage.SEVERITY_ERROR, 
				getValueFromKey(EXCEPTION), 
				view.generateReadableExceptionMessage(exception, user));
	}
	
	/**
	 * Test method for {@link BackingListSeller#onRowEdit(RowEditEvent)} with id null.
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
		when(event.getObject()).thenReturn(user);
		// call to onRowEdit()
		view.onRowEdit(event);
		// verify Service.update() was called
		verify(view.userService).update(any(user.getClass()));
		// check if a FacesMessage was correctly thrown
		TestBackingBean.checkMessageWithPlainDetail(view, null, FacesMessage.SEVERITY_ERROR, 
				getValueFromKey(EXCEPTION), 
				view.generateReadableExceptionMessage(exception, user));
	}
	
	/**
	 * Test method for {@link BackingListSeller#onRowEdit(RowEditEvent)} with id not null.
	 * @throws Exception should not be thrown.
	 */
	@Test
	public final void testOnRowEditWithIdNotNull() throws Exception {
		// initialization
		setInjections();
		RowEditEvent event = mock(RowEditEvent.class);
		when(event.getObject()).thenReturn(user);
		// call to onRowEdit()
		view.onRowEdit(event);
		// verify Service.update() was called
		verify(view.userService).update(any(user.getClass()));
		// check if a FacesMessage was correctly thrown
		TestBackingBean.checkMessageWithParametrizedDetail(view, null, FacesMessage.SEVERITY_INFO, 
				getValueFromKey(UPDATE_OK), getValueFromKey(getConstantsEntity()), getValueFromKey(UPDATE));
	}
	
	/**
	 * Test method for {@link BackingListSeller#delete()} with current customer null.
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
	 * Test method for {@link BackingListSeller#delete()} with entity null.
	 * @throws Exception should not be thrown.
	 */
	@Test
	public final void testDeleteWithEntityNull() throws Exception {
		// initialization
		setInjections();
		view.setCurrentUser(user);
		// Simulate an exception thrown by service
		ExceptionService exception = TestExceptionService.createMockedExceptionEntityShouldNotBeNull();
		doThrow(exception).when(view.userService).delete(any(user.getClass()));
		// call to delete()
		view.delete();
		// verify Service.delete() was called
		verify(view.userService).delete(any(user.getClass()));
		// check if a FacesMessage was correctly thrown
		TestBackingBean.checkMessageWithPlainDetail(view, null, FacesMessage.SEVERITY_ERROR, 
				getValueFromKey(EXCEPTION), 
				view.generateReadableExceptionMessage(exception, user));
	}
	
	/**
	 * Test method for {@link BackingListSeller#delete()} with entity's id null.
	 * @throws Exception should not be thrown.
	 */
	@Test
	public final void testDeleteWithIdNull() throws Exception {
		// initialization
		setInjections();
		view.setCurrentUser(user);
		// Simulate an exception thrown by service
		ExceptionService exception = TestExceptionService.createMockedExceptionIdShouldNotBeNull();
		doThrow(exception).when(view.userService).delete(any(user.getClass()));
		// call to delete()
		view.delete();
		// verify Service.delete() was called
		verify(view.userService).delete(any(user.getClass()));
		// check if a FacesMessage was correctly thrown
		TestBackingBean.checkMessageWithPlainDetail(view, null, FacesMessage.SEVERITY_ERROR, 
				getValueFromKey(EXCEPTION), 
				view.generateReadableExceptionMessage(exception, user));
	}
	
	/**
	 * Test method for {@link BackingListSeller#delete()} with entity's id not null.
	 * @throws Exception should not be thrown.
	 */
	@Test
	public final void testDeleteWithIdNotNull() throws Exception {
		// initialization
		setInjections();
		view.setCurrentUser(user);
		// call to delete()
		view.delete();
		// verify Service.delete() was called
		verify(view.userService).delete(any(user.getClass()));
		// check if a FacesMessage was correctly thrown
		TestBackingBean.checkMessageWithParametrizedDetail(view, null, FacesMessage.SEVERITY_INFO, 
				getValueFromKey(DELETE_OK), getValueFromKey(getConstantsEntity()), getValueFromKey(DELETE));
	}
	
	// Helpers //-------------------------------------------------
	/**
	 * Set mocked {@link ServiceUser}, and a dummy {@link java.util.logging.Logger} into view.
	 * Call {@link TestBackingBean#setInjections(AbstractView)}.
	 */
	private void setInjections() {
		TestBackingBean.setInjections(view);
		view.userService = mock(ServiceUser.class);
		view.logger = TestResources.createLogger(view.getClass());
	}
	
	/**
	 * Retrieve the appropriate value from the enumeration {@link Entity} using the {@code user}.
	 * @return the {@link Entity}.
	 */
	private Entity getConstantsEntity() {
		Entity entity = null;
		if (user instanceof Customer) {
			entity = Entity.CUSTOMER;
		} else if (user instanceof Seller) {
			entity = Entity.SELLER;
		} else if (user instanceof Administrator) {
			entity = Entity.ADMINISTRATOR;
		}
		return entity;
	}
	
	// Static methods //------------------------------------------
	/**
	 * Reference a list of all {@link BackingListUser}'s concrete children, 
	 * and the associated concrete child of {@link AbstractUser},
	 * to be used as parameters on constructor.
	 * Each iteration will be tested with all test methods.
	 * @return {@code Iterable<Object[]>} with both parameters.
	 */
	@Parameters
	public static Iterable<Object[]> childToTest() {
		return Arrays.asList(new Object[][] {
			{new BackingListCustomer(), new Customer()},
			{new BackingListSeller(), new Seller()}
			}
		);
	}
}