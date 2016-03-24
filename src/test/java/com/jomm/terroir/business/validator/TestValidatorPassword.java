package com.jomm.terroir.business.validator;

import static com.jomm.terroir.util.Constants.ResourceBundleError.FIELD_MANDATORY;
import static com.jomm.terroir.util.Constants.ResourceBundleError.PASSWORDS_DONT_MATCH;
import static com.jomm.terroir.util.Constants.ResourceBundleError.PASSWORD_NOT_MATCHING_PATTERN;
import static com.jomm.terroir.util.Constants.View.PASSWORD_PARAMETER;
import static com.jomm.terroir.util.Resources.getValueFromKey;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;

import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.UIInput;
import javax.faces.validator.ValidatorException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * This class is a Junit test case testing the {@code validate()} method of {@link ValidatorPassword}.
 * It is annotated {@link RunWith} {@link Parameterized} to allow the test case to run with different parameters.
 * Here, the parameters are different user names to be tested, and their expected results.
 * @author Maic
 */
@RunWith(Parameterized.class)
public class TestValidatorPassword {

	/** Enumeration of different possible results for {@code validate()} method of {@link ValidatorPassword}. */
	private enum ExpectedResult {
		/** At least one password is null or empty, thus the validation should fail. */
		FAILURE_NULL_OR_EMPTY,
		/** The passwords differ, thus the validation should fail. */
		FAILURE_PASSWORDS_NOT_MATCHING,
		/** The value is not a valid pattern, thus the validation should fail. */
		FAILURE_PATTERN,
		/** The value is a valid pattern, thus the validation should succeed. */
		SUCCESS
	};
	
	// Attributes
	private ValidatorPassword validator;
	private String password1;
	private String password2;
	private ExpectedResult expectedResult;

	/**
	 * Constructor.
	 * Its parameter comes from all values from {@link TestValidatorPassword#valueToTest()}.
	 * @param password1 String the password1 to be tested.
	 * @param password2 String the password2 to be tested.
	 * @param expectedResult Result the expected result of the validation.
	 */
	public TestValidatorPassword(String password1, String password2, ExpectedResult expectedResult) {
		// Set the parameters for the test
		this.password1 = password1;
		this.password2 = password2;
		this.expectedResult = expectedResult;
		// Initialize the validator
		validator = new ValidatorPassword();
	}

	/**
	 * Test method {@link ValidatorPassword#validate(javax.faces.context.FacesContext, 
	 * javax.faces.component.UIComponent, java.lang.Object)}.
	 */
	@Test
	public final void testValidateWithDifferentValues() {
		try {
			validator.validate(null, buildPassword1Component(password1), password2); // here a ValidatorException can occur
			switch (expectedResult) {
			case SUCCESS: // This is expected
				assertTrue("ValidatorException is not thrown, which is expected with password1=" + password1 
						+ ", password2=" + password2, true);				
				break;
			case FAILURE_NULL_OR_EMPTY: // This is NOT expected
			case FAILURE_PASSWORDS_NOT_MATCHING: // This is NOT expected
			case FAILURE_PATTERN: // This is NOT expected
				fail("ValidatorException was not thrown and should have with password1=" + password1 
						+ ", password2=" + password2);				
				break;
			}
		} catch (ValidatorException expectedException) {
			switch (expectedResult) {
			case SUCCESS: // This is NOT expected
				fail("ValidatorException was thrown and should not have with password1=" + password1 
						+ ", password2=" + password2 + ", " + expectedException.getFacesMessage().getSummary());		
				break;
			case FAILURE_NULL_OR_EMPTY: // This is expected
				assertEquals("ValidatorException was thrown, which is expected with password1=" + password1 
						+ ", password2=" + password2, getValueFromKey(FIELD_MANDATORY), 
						expectedException.getFacesMessage().getSummary());		
				break;
			case FAILURE_PASSWORDS_NOT_MATCHING: // This is expected
				assertEquals("ValidatorException was thrown, which is expected with password1=" + password1 
						+ ", password2=" + password2, getValueFromKey(PASSWORDS_DONT_MATCH), 
						expectedException.getFacesMessage().getSummary());		
				break;
			case FAILURE_PATTERN: // This is expected
				assertEquals("ValidatorException was thrown, which is expected with password=" + password1, 
						getValueFromKey(PASSWORD_NOT_MATCHING_PATTERN), 
						expectedException.getFacesMessage().getSummary());		
				break;
			}
		}
	}

	/**
	 * Reference a list of pair of passwords, associated with their expected result to the test, 
	 * to be used as parameters on constructor.
	 * Each pair of passwords will be tested with the test {@link TestValidatorPassword#testValidateWithDifferentValues()}.
	 * @return {@code Iterable<Object[]>} with the parameters.
	 */
	@Parameters
	public static Iterable<Object[]> valueToTest() {
		return Arrays.asList(new Object[][] {
			// The validation must fail with null or empty values
			{null, "Za@9xx", ExpectedResult.FAILURE_NULL_OR_EMPTY},
			{"", "Za@9xx", ExpectedResult.FAILURE_NULL_OR_EMPTY},
			{"Za@9xx", null, ExpectedResult.FAILURE_NULL_OR_EMPTY},
			{"Za@9xx", "", ExpectedResult.FAILURE_NULL_OR_EMPTY},
			// The validation must fail with values that don't match
			{"Us3rN@me", "azAZ09@#$%^&+=", ExpectedResult.FAILURE_PASSWORDS_NOT_MATCHING},
			// The validation must fail with incorrect values
			{"PASSWORD3#", "PASSWORD3#", ExpectedResult.FAILURE_PATTERN},
			{"Password3", "Password3", ExpectedResult.FAILURE_PATTERN},
			{"password3#", "password3#", ExpectedResult.FAILURE_PATTERN},
			{"Password#", "Password#", ExpectedResult.FAILURE_PATTERN},
			// The validation must succeed with correct pair of values
			{"Password3#", "Password3#", ExpectedResult.SUCCESS},
			{"Us3rN@me", "Us3rN@me", ExpectedResult.SUCCESS},
			{"azAZ09@#$%^&+=", "azAZ09@#$%^&+=", ExpectedResult.SUCCESS}
		});
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
		component.getAttributes().put(PASSWORD_PARAMETER.getId(), uiInput);
		return component;
	}
}