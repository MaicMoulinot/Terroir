package com.jomm.terroir.business.validator;

import static com.jomm.terroir.util.Constants.ResourceBundleError.EMAIL_UNVALID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

import javax.faces.validator.ValidatorException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.jomm.terroir.business.ServiceUser;
import com.jomm.terroir.util.Resources;
import com.jomm.terroir.util.TestResources;

/**
 * This class is a Junit test case testing the {@code validate()} method of {@link ValidatorEmail}.
 * It is annotated {@link RunWith} {@link MockitoJUnitRunner} to explicit usage of Mockito annotations.
 * @author Maic
 */
@RunWith(MockitoJUnitRunner.class)
public class TestValidatorEmail {
	
	@Mock
    private ServiceUser service;
	
	@InjectMocks
	private ValidatorEmail validator;

	/**
	 * Set proper ResourceBundle for the validator
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		validator.setResourceBundle(Resources.getResourceBundleError());
	}
	
	/**
	 * Test with Email null for {@link ValidatorEmail#validate(javax.faces.context.FacesContext, 
	 * javax.faces.component.UIComponent, java.lang.Object)}.
	 */
	@Test
	public final void testValidateWithEmailNull() {
		try {
			validator.validate(null, null, null);
			assertTrue(true); // Assert no ValidatorException was thrown
		} catch (ValidatorException expectedException) {
			assertNull("ValidatorException was thrown and should not have with Email null or empty", expectedException);
		}
	}
	
	/**
	 * Test with Email empty for {@link ValidatorEmail#validate(javax.faces.context.FacesContext, 
	 * javax.faces.component.UIComponent, java.lang.Object)}.
	 */
	@Test
	public final void testValidateWithEmailEmpty() {
		try {
			validator.validate(null, null, "");
			assertTrue(true); // Assert no ValidatorException was thrown
		} catch (ValidatorException expectedException) {
			assertNull("ValidatorException was thrown and should not have with Email null or empty", expectedException);
		}
	}
	
	/**
	 * Test with Email not matching pattern for {@link ValidatorEmail#validate(javax.faces.context.FacesContext, 
	 * javax.faces.component.UIComponent, java.lang.Object)}.
	 */
	@Test
	public final void testValidateWithEmailNotMatchingPattern() {		
		// Test with Email not matching pattern ("Email")
		try {
			validator.validate(null, null, "Email");
			// Should throw a ValidatorException. If not fail the test
			fail("ValidatorException was not thrown and should have with email non valid");
		} catch (ValidatorException expectedException) {
			assertEquals(TestResources.getResourceBundleError(EMAIL_UNVALID.getKey()), 
					expectedException.getFacesMessage().getSummary());
		}
	}
	
	/**
	 * Test with Email matching pattern for {@link ValidatorEmail#validate(javax.faces.context.FacesContext, 
	 * javax.faces.component.UIComponent, java.lang.Object)}.
	 */
	@Test
	public final void testValidateWithEmailMatchingPattern() {
		// Test with Email matching pattern ("email@email.com")
		try {
			validator.validate(null, null, "email@email.com");
			verify(service).isExistingEmail(anyString()); // validate that service.isExistingEmail() was called
		} catch (ValidatorException expectedException) {
			assertNull("ValidatorException was thrown and should not have", expectedException);
		}
	}
}