package com.jomm.terroir.business.validator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

import java.util.ResourceBundle;

import javax.faces.validator.ValidatorException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.jomm.terroir.business.ServiceUser;
import com.jomm.terroir.util.Resources;

/**
 * This class is a Junit test case testing the <code>validate()</code> method of {@link ValidatorUsername}.
 * It is annotated {@link RunWith} {@link MockitoJUnitRunner} to explicit usage of Mockito annotations.
 * @author Maic
 */
@RunWith(MockitoJUnitRunner.class)
public class TestValidatorUsername {
	
	@Mock(name = "service")
    private ServiceUser service;
	
	@InjectMocks
	private ValidatorUsername validator;
	
	private ResourceBundle resource;

	/**
	 * Set proper ResourceBundle for the validator
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		resource = Resources.getResourceBundleError();
		validator.setResourceBundle(resource);
	}

	/**
	 * Test with Username null for {@link ValidatorUsername#validate(javax.faces.context.FacesContext, 
	 * javax.faces.component.UIComponent, java.lang.Object)}.
	 */
	@Test
	public final void testValidateWithUsernameNull() {
		// Test with Username null and empty
		try {
			validator.validate(null, null, null);
			assertTrue(true); // Assert no ValidatorException was thrown
		} catch (ValidatorException expectedException) {
			assertNull("ValidatorException was thrown and should not have with username null or empty", expectedException);
		}
	}
	
	/**
	 * Test with Username empty for {@link ValidatorUsername#validate(javax.faces.context.FacesContext, 
	 * javax.faces.component.UIComponent, java.lang.Object)}.
	 */
	@Test
	public final void testValidateWithUsernameEmpty() {
		try {
			validator.validate(null, null, "");
			assertTrue(true); // Assert no ValidatorException was thrown
		} catch (ValidatorException expectedException) {
			assertNull("ValidatorException was thrown and should not have with username null or empty", expectedException);
		}
	}
	
	/**
	 * Test with Username's length < 6 for {@link ValidatorUsername#validate(javax.faces.context.FacesContext, 
	 * javax.faces.component.UIComponent, java.lang.Object)}.
	 */
	@Test
	public final void testValidateWithUsernameTooShort() {
		// Test with Username's length < 6
		try {
			validator.validate(null, null, "user");
			// Should throw a ValidatorException. If not fail the test
			fail("ValidatorException was not thrown and should have with lenght < 6");
		} catch (ValidatorException expectedException) {
			assertEquals(resource.getString(ValidatorUsername.LENGTH_AT_LEAST_6_CHARACTERS), 
					expectedException.getFacesMessage().getSummary());
		}
	}
	
	/**
	 * Test with Username's length > 6 for {@link ValidatorUsername#validate(javax.faces.context.FacesContext, 
	 * javax.faces.component.UIComponent, java.lang.Object)}.
	 */
	@Test
	public final void testValidateWithUsernameLongEnough() {
		try {
			validator.validate(null, null, "UserName");
			verify(service).isExistingUserName(anyString()); // validate that service.isExistingUserName() was called
		} catch (ValidatorException expectedException) {
			assertNull("ValidatorException was thrown and should not have", expectedException);
		}
	}
}