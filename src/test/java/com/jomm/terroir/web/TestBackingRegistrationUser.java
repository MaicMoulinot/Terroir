package com.jomm.terroir.web;

import static com.jomm.terroir.util.Constants.ResourceBundleError.EXCEPTION;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.CREATE;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.CREATE_OK;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.PASSWORD_RULES;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.PASSWORD_TITLE;
import static com.jomm.terroir.util.Constants.View.GROWL;
import static com.jomm.terroir.util.Resources.getValueFromKey;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import javax.faces.application.FacesMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.w3c.dom.views.AbstractView;

import com.jomm.terroir.business.ServiceUser;
import com.jomm.terroir.business.model.AbstractUser;
import com.jomm.terroir.business.model.Administrator;
import com.jomm.terroir.business.model.Customer;
import com.jomm.terroir.business.model.Seller;
import com.jomm.terroir.util.Constants.Entity;
import com.jomm.terroir.util.TestResources;
import com.jomm.terroir.util.exception.ExceptionService;
import com.jomm.terroir.util.exception.TestExceptionService;

/**
 * This class is a Junit test case testing the methods of {@link BackingRegistrationUser}.
 * It is annotated {@link RunWith} {@link Parameterized} to allow the test case to run with different parameters.
 * Here, the parameters are each child of {@link BackingRegistrationUser} with its associated concrete child of {@link AbstractUser}.
 * @author Maic
 */
@RunWith(Parameterized.class)
public class TestBackingRegistrationUser {
	
	// Variables //-----------------------------------------------
	private BackingRegistrationUser view;
	private AbstractUser user;
	
	// Constructors //--------------------------------------------
	/**
	 * Constructor.
	 * Its parameter comes from all values from {@link TestBackingRegistrationUser#childToTest()}.
	 * @param view the concrete child of {@link BackingRegistrationUser}.
	 * @param user the concrete child of {@link AbstractUser}.
	 */
    public TestBackingRegistrationUser(BackingRegistrationUser view, AbstractUser user) {
        this.view = view;
        this.user = user;
    }
    
    // Test methods //--------------------------------------------
	/**
	 * Test method for {@link BackingRegistrationCustomer#create()} when entity is null.
	 * @throws Exception should not be thrown.
	 */
	@Test
	public final void testCreateWithEntityNull() throws Exception {
		// initialization
		setInjections();
		// Simulate an exception thrown by service
		ExceptionService exception = TestExceptionService.createMockedExceptionEntityShouldNotBeNull();
		when(view.userService.create(any(user.getClass()))).thenThrow(exception);
		// call to create()
		view.create();
		// verify Service.create() was called
		verify(view.userService).create(any(user.getClass()));
		// check if a FacesMessage was correctly thrown
		TestBackingBean.checkMessageWithPlainDetail(view, null, FacesMessage.SEVERITY_ERROR, 
				getValueFromKey(EXCEPTION), 
				view.generateReadableExceptionMessage(exception, user));
	}

	/**
	 * Test method for {@link BackingRegistrationCustomer#create()} with id not null.
	 * @throws Exception should not be thrown.
	 */
	@Test
	public final void testCreateWithIdNotNull() throws Exception {
		// initialization
		setInjections();
		// Simulate an exception thrown by service
		ExceptionService exception = TestExceptionService.createMockedExceptionIdShouldBeNull();
		when(view.userService.create(any(user.getClass()))).thenThrow(exception);
		// call to create()
		view.create();
		// verify Service.create() was called
		verify(view.userService).create(any(user.getClass()));
		// check if a FacesMessage was correctly thrown
		TestBackingBean.checkMessageWithPlainDetail(view, null, FacesMessage.SEVERITY_ERROR, 
				getValueFromKey(EXCEPTION), 
				view.generateReadableExceptionMessage(exception, user));
	}

	/**
	 * Test method for {@link BackingRegistrationCustomer#create()} with id null.
	 * @throws Exception should not be thrown.
	 */
	@Test
	public final void testCreateWithIdNull() throws Exception {
		// initialization
		setInjections();
		// call to create()
		view.create();
		// verify Service.create() was called
		verify(view.userService).create(any(user.getClass()));
		// check if a FacesMessage was correctly thrown
		TestBackingBean.checkMessageWithParametrizedDetail(view, null, FacesMessage.SEVERITY_INFO, 
				getValueFromKey(CREATE_OK), getValueFromKey(getConstantsEntity()), getValueFromKey(CREATE));
	}

	/**
	 * Test method for {@link BackingRegistrationUser#passwordTooltip()}.
	 */
	@Test
	public final void testPasswordTooltip() {
		// initialization
		setInjections();
		// call
		view.passwordTooltip();
		// check if a FacesMessage was correctly thrown
		TestBackingBean.checkMessageWithPlainDetail(view, GROWL.toString(), FacesMessage.SEVERITY_INFO, 
				getValueFromKey(PASSWORD_TITLE), 
				getValueFromKey(PASSWORD_RULES).replaceAll("-", "<br />-"));
	}

	/**
	 * Test method for {@link BackingRegistrationUser}'s getters and setters.
	 */
	@Test
	public final void testGetterSetter() {
		String test = "test";
		
		// FirstName
		view.setFirstName(test);
		assertEquals("FirstName should be " + test, test, view.getFirstName());
		
		// LastName
		view.setLastName(test);
		assertEquals("LastName should be " + test, test, view.getLastName());
		
		// UserName
		view.setUserName(test);
		assertEquals("UserName should be " + test, test, view.getUserName());
		
		// Email
		view.setEmail(test);
		assertEquals("Email should be " + test, test, view.getEmail());
		
		// Password
		view.setPassword(test);
		assertEquals("Password should be " + test, test, view.getPassword());
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
	 * Generate a dummy {@link BackingRegistrationUser} usable for tests.
	 * @param user the {@link AbstractUser} defining appropriate view.
	 * @return {@link BackingRegistrationUser}.
	 */
	static BackingRegistrationUser generateDummyViewUser(AbstractUser user) {
		BackingRegistrationUser view = null;
		if (user != null) {
			if (user instanceof Seller) {
				view = TestBackingRegistrationSeller.generateDummyViewSeller();
			} else if (user instanceof Customer) {
				view = TestBackingRegistrationCustomer.generateDummyViewCustomer();
			}
		}
		return view;
	}
	
	/**
	 * Set dummy values to a {@link BackingRegistrationUser} usable for tests.
	 * @param view the {@link BackingRegistrationUser} to be set.
	 */
	static void setDummyValues(BackingRegistrationUser view) {
		view.setFirstName("FirstName");
		view.setLastName("LastName");
		view.setUserName("UserName");
		view.setEmail("email@email.com");
		view.setPassword("Z@bf0sdfZnd");
	}
	
	/**
	 * Reference a list of all {@link BackingRegistrationUser}'s concrete children, 
	 * and the associated concrete child of {@link AbstractUser},
	 * to be used as parameters on constructor.
	 * Each iteration will be tested with all test methods.
	 * @return {@code Iterable<Object[]>} with both parameters.
	 */
	@Parameters
	public static Iterable<Object[]> childToTest() {
		return Arrays.asList(new Object[][] {
			{new BackingRegistrationCustomer(), new Customer()},
			{new BackingRegistrationSeller(), new Seller()}
			}
		);
	}
}