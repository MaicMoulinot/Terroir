package com.jomm.terroir.business.validator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.UIInput;
import javax.faces.validator.ValidatorException;

import org.junit.Before;
import org.junit.Test;

import com.jomm.terroir.util.Constants;
import com.jomm.terroir.util.Resources;

/**
 * This class is a Junit test case testing the <code>validate()</code> method of {@link ValidatorPassword}.
 * @author Maic
 */
public class TestValidatorPassword {

	private ValidatorPassword validator;

	/**
	 * Instantiate the ValidatorPassword and set proper ResourceBundle.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		validator = new ValidatorPassword();
		validator.resource = Resources.getResourceBundleError();
	}
	
	/**
	 * Test with Password1 null for 
	 * {@link ValidatorPassword#validate(javax.faces.context.FacesContext, UIComponent, Object)}.
	 */
	@Test
	public final void testValidateWithPassword1Null() {
		try {
			validator.validate(null, buildPassword1Component(null), "Password2");
			// Should throw a ValidatorException. If not fail the test
			fail("ValidatorException was not thrown and should have with password1 null");
		} catch (ValidatorException expectedException) {
			assertEquals(validator.resource.getString(Constants.FIELD_MANDATORY), 
					expectedException.getFacesMessage().getSummary());
		}
	}
	
	/**
	 * Test with Password1 empty for 
	 * {@link ValidatorPassword#validate(javax.faces.context.FacesContext, UIComponent, Object)}.
	 */
	@Test
	public final void testValidateWithPassword1Empty() {		
		try {
			validator.validate(null, buildPassword1Component(""), "Password2");
			// Should throw a ValidatorException. If not fail the test
			fail("ValidatorException was not thrown and should have with password1 empty");
		} catch (ValidatorException expectedException) {
			assertEquals(validator.resource.getString(Constants.FIELD_MANDATORY), 
					expectedException.getFacesMessage().getSummary());
		}
	}
	
	/**
	 * Test with Password2 null for 
	 * {@link ValidatorPassword#validate(javax.faces.context.FacesContext, UIComponent, Object)}.
	 */
	@Test
	public final void testValidateWithPassword2Null() {
		try {
			validator.validate(null, buildPassword1Component("Password1"), null);
			// Should throw a ValidatorException. If not fail the test
			fail("ValidatorException was not thrown and should have with password2 null");
		} catch (ValidatorException expectedException) {
			assertEquals(validator.resource.getString(Constants.FIELD_MANDATORY), 
					expectedException.getFacesMessage().getSummary());
		}
	}
	
	/**
	 * Test with Password2 empty for 
	 * {@link ValidatorPassword#validate(javax.faces.context.FacesContext, UIComponent, Object)}.
	 */
	@Test
	public final void testValidateWithPassword2Empty() {
		try {
			validator.validate(null, buildPassword1Component("Password1"), "");
			// Should throw a ValidatorException. If not fail the test
			fail("ValidatorException was not thrown and should have with password2 empty");
		} catch (ValidatorException expectedException) {
			assertEquals(validator.resource.getString(Constants.FIELD_MANDATORY), 
					expectedException.getFacesMessage().getSummary());
		}
	}
	
	/**
	 * Test with Password1 and Password2 not matching for 
	 * {@link ValidatorPassword#validate(javax.faces.context.FacesContext, UIComponent, Object)}.
	 */
	@Test
	public final void testValidateWithPassword1AndPassword2NotMatching() {
		try {
			validator.validate(null, buildPassword1Component("Password1"), "Password2");
			// Should throw a ValidatorException. If not fail the test
			fail("ValidatorException was not thrown and should have with password1 and password2 different");
		} catch (ValidatorException expectedException) {
			assertEquals(validator.resource.getString(Constants.PASSWORDS_DONT_MATCH), 
					expectedException.getFacesMessage().getSummary());
		}
	}
	
	/**
	 * Test with Password not matching pattern for 
	 * {@link ValidatorPassword#validate(javax.faces.context.FacesContext, UIComponent, Object)}.
	 */
	@Test
	public final void testValidateWithPasswordNotMatchingPattern() {
		try {
			String password = "password";
			validator.validate(null, buildPassword1Component(password), password);
			// Should throw a ValidatorException. If not fail the test
			fail("ValidatorException was not thrown and should have password not matching the pattern");
		} catch (ValidatorException expectedException) {
			assertEquals(validator.resource.getString(Constants.PASSWORD_TOO_SIMPLE), 
					expectedException.getFacesMessage().getSummary());
		}
	}
	
	/**
	 * Test with Password matching pattern for 
	 * {@link ValidatorPassword#validate(javax.faces.context.FacesContext, UIComponent, Object)}.
	 */
	@Test
	public final void testValidateWithPasswordMatchingPattern() {
		try {
			String password = "Za3@hGm450@";
			validator.validate(null, buildPassword1Component(password), password);
			assertNull(null); // Assert no ValidatorException was thrown
		} catch (ValidatorException expectedException) {
			assertNull("ValidatorException was thrown and should have with password matching the pattern", 
					expectedException);
		}
	}
	
	/**
	 * Build a stubbed {@link UIComponent} usable for tests.
	 * @param password1 String the Password1.
	 * @return the built UIComponent.
	 */
	private UIComponent buildPassword1Component(String password1) {
		// Create UIComponent
		UIComponent component = new UIComponentBase() {
			@Override
			public String getFamily() {
				return "TestComponent";
			}
		};
		// Create UIInput
		UIInput uiInput = new UIInput();
		uiInput.setValue(password1);
		// Set UIInput to UIComponent
		component.getAttributes().put(Constants.PASSWORD_PARAMETER, uiInput);
		return component;
	}
}