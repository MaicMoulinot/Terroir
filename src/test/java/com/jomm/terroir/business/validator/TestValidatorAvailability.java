package com.jomm.terroir.business.validator;

import static com.jomm.terroir.util.Constants.ResourceBundleError.AVAILABILITY;
import static com.jomm.terroir.util.Constants.View.PARAMETER1;
import static com.jomm.terroir.util.Resources.getValueFromKey;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

import java.util.Arrays;

import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * This class is a Junit test case testing the {@code validate()} method of {@link ValidatorAvailability}.
 * It is annotated {@link RunWith} {@link Parameterized} to allow the test case to run with different parameters.
 * Here, the parameters are different user names to be tested, and their expected results.
 * @author Maic
 */
@RunWith(Parameterized.class)
public class TestValidatorAvailability {

	// Constants //-----------------------------------------------
	/** Enumeration of different possible results for {@code validate()} method of {@link ValidatorAvailability}. */
	private enum ExpectedResult {
		/** Active is null or false, thus the validation should not fail. */
		SUCCESS_ACTIVE_NULL_OR_FALSE,
		/** Availability is not positive with an active state, thus the validation should fail. */
		FAILURE_AVAILABILITY_NOT_POSITIVE,
		/** The availability is valid, thus the validation should succeed. */
		SUCCESS
	};
	
	// Variables //-----------------------------------------------
	private ValidatorAvailability validator;
	private Integer availability;
	private Boolean isActive;
	private ExpectedResult expectedResult;

	// Constructors //--------------------------------------------
	/**
	 * Constructor.
	 * Its parameter comes from all values from {@link TestValidatorAvailability#valueToTest()}.
	 * @param availability Integer the availability to be tested.
	 * @param isActive Boolean the product's state to be tested.
	 * @param expectedResult Result the expected result of the validation.
	 */
	public TestValidatorAvailability(Integer availability, Boolean isActive, ExpectedResult expectedResult) {
		// Set the parameters for the test
		this.availability = availability;
		this.isActive = isActive;
		this.expectedResult = expectedResult;
		// Initialize the validator
		validator = new ValidatorAvailability();
	}

	// Test methods //--------------------------------------------
	/**
	 * Test method {@link ValidatorAvailability#validate(FacesContext, UIComponent, Object)}.
	 */
	@Test
	public final void testValidateWithDifferentValues() {
		try {
			// here a ValidatorException can occur
			validator.validate(mock(FacesContext.class), buildAvailabilityComponent(isActive), availability);
			switch (expectedResult) {
			case SUCCESS: // This is expected
			case SUCCESS_ACTIVE_NULL_OR_FALSE: // This is expected
				assertTrue("ValidatorException is not thrown, which is expected with isActive=" + isActive 
						+ ", availability=" + availability, true);				
				break;
			case FAILURE_AVAILABILITY_NOT_POSITIVE: // This is NOT expected
				fail("ValidatorException was not thrown and should have with isActive=" + isActive 
						+ ", availability=" + availability);				
				break;
			}
		} catch (ValidatorException expectedException) {
			switch (expectedResult) {
			case SUCCESS: // This is NOT expected
			case SUCCESS_ACTIVE_NULL_OR_FALSE: // This is NOT expected
				fail("ValidatorException was thrown and should not have with isActive=" + isActive 
						+ ", availability=" + availability + ", " + expectedException.getFacesMessage().getSummary());		
				break;
			case FAILURE_AVAILABILITY_NOT_POSITIVE: // This is expected
				assertEquals("ValidatorException was thrown, which is expected with isActive=" + isActive 
						+ ", availability=" + availability, getValueFromKey(AVAILABILITY), 
						expectedException.getFacesMessage().getSummary());		
				break;
			}
		}
	}

	// Static methods //------------------------------------------
	/**
	 * Reference a list of availability and product's state, associated with their expected result to the test, 
	 * to be used as parameters on constructor.
	 * Each pair of passwords will be tested with the test 
	 * {@link TestValidatorAvailability#testValidateWithDifferentValues()}.
	 * @return {@code Iterable<Object[]>} with the parameters.
	 */
	@Parameters
	public static Iterable<Object[]> valueToTest() {
		return Arrays.asList(new Object[][] {
			// The validation must not fail with null or empty values
			{14, null, ExpectedResult.SUCCESS_ACTIVE_NULL_OR_FALSE},
			{14, false, ExpectedResult.SUCCESS_ACTIVE_NULL_OR_FALSE},
			// The validation must fail with null, negative or zero availability and active state
			{null, true, ExpectedResult.FAILURE_AVAILABILITY_NOT_POSITIVE},
			{-14, true, ExpectedResult.FAILURE_AVAILABILITY_NOT_POSITIVE},
			{0, true, ExpectedResult.FAILURE_AVAILABILITY_NOT_POSITIVE},
			// The validation must succeed with correct values
			{14, true, ExpectedResult.SUCCESS}
		});
	}
	
	// Helpers //-------------------------------------------------
	/**
	 * Build a stubbed {@link UIComponent} usable for tests.
	 * @param isActive Boolean defines if the product is active for sale.
	 * @return the built UIComponent.
	 */
	private UIComponent buildAvailabilityComponent(Boolean isActive) {
		// Create UIComponent
		UIComponent component = new UIComponentBase() {
			@Override
			public String getFamily() {
				return "TestComponent";
			}
		};
		// Create UIInput
		UIInput uiInput = new UIInput();
		uiInput.setValue(isActive);
		// Set UIInput to UIComponent
		component.getAttributes().put(PARAMETER1.toString(), uiInput);
		return component;
	}
}