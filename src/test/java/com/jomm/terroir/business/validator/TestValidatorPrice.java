package com.jomm.terroir.business.validator;

import static com.jomm.terroir.util.Constants.ResourceBundleError.MANDATORY;
import static com.jomm.terroir.util.Constants.ResourceBundleError.NUMBER_GREATER_THAN_ZERO;
import static com.jomm.terroir.util.Constants.ResourceBundleError.PRICE_OUT_OF_RANGE;
import static com.jomm.terroir.util.Constants.ResourceBundleError.UNIT_QUANTITY_DESIGNATION_MANDATORY;
import static com.jomm.terroir.util.Constants.View.PARAMETER1;
import static com.jomm.terroir.util.Constants.View.PARAMETER2;
import static com.jomm.terroir.util.Constants.View.PARAMETER3;
import static com.jomm.terroir.util.Constants.View.PARAMETER4;
import static com.jomm.terroir.util.Resources.getValueFromKey;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
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

import com.jomm.terroir.business.ServiceDesignation;
import com.jomm.terroir.business.model.Designation;
import com.jomm.terroir.business.model.TestDesignation;
import com.jomm.terroir.util.Constants.Unit;
import com.jomm.terroir.util.exception.ExceptionService;

/**
 * This class is a Junit test case testing the {@code validateChangedObject()} method of {@link ValidatorPrice}.
 * It is annotated {@link RunWith} {@link Parameterized} to allow the test case to run with different parameters.
 * Here, the parameters are different user names to be tested, and their expected results.
 * @author Maic
 */
@RunWith(Parameterized.class)
public class TestValidatorPrice {

	// Constants //-----------------------------------------------
	/** 
	 * Enumeration of different possible results for {@code validateChangedObject()} 
	 * method of {@link ValidatorPrice}.
	 */
	private enum ExpectedResult {
		/** When the validation is enabled, if the price is null, the validation should fail. */
		FAILURE_PRICE_NULL,
		/** When the validation is enabled, if the price is not positive, the validation should fail. */
		FAILURE_PRICE_ZERO_OR_NEGATIVE,
		/** When the validation is enabled, if the quantity, unit or designation is missing, 
		 * the validation should fail. */
		FAILURE_OTHER_INPUTS_ARE_MISSING,
		/** When the validation is enabled, if the price is out of range, the validation should fail. */
		FAILURE_PRICE_OUT_OF_RANGE,
		/** When the validation is enabled, if the values are valid, the validation should succeed. */
		SUCCESS,
		/** The doValidation is false, thus the validation should not fail. */
		VALIDATION_DISABLED
	};

	// Variables //-----------------------------------------------
	private ValidatorPrice validator;
	private ServiceDesignation service;
	private Designation designation;
	private BigDecimal quantity;
	private Unit unit;
	private BigDecimal price;
	private boolean doValidation;
	private ExpectedResult expectedResult;

	// Constructors //--------------------------------------------
	/**
	 * Constructor.
	 * Its parameter comes from all values from {@link TestValidatorPrice#valueToTest()}.
	 * @param designation the {@link Designation}.
	 * @param quantity {@link BigDecimal} the quantity.
	 * @param unit the {@link Unit}.
	 * @param price {@link BigDecimal} the price.
	 * @param doValidation boolean enables the validation.
	 * @param expectedResult Result the expected result of the validation.
	 * @throws ExceptionService when {@code validatePrice()} throws an exception.
	 */
	public TestValidatorPrice(Designation designation, BigDecimal quantity, Unit unit, BigDecimal price, 
			boolean doValidation, ExpectedResult expectedResult) throws ExceptionService {
		// Set the parameters for the test
		this.designation = designation;
		this.quantity = quantity;
		this.unit = unit;
		this.price = price;
		this.doValidation = doValidation;
		this.expectedResult = expectedResult;
		// Initialize the validator and mocked service
		boolean result = (expectedResult == ExpectedResult.SUCCESS) ? true : false;
		service = mock(ServiceDesignation.class);
		when(service.validatePrice(any(), any(), any())).thenReturn(result);
		validator = new ValidatorPrice();
		validator.setTestServiceDesignation(service);
	}

	// Test methods //--------------------------------------------
	/**
	 * Test method {@link ValidatorPrice#validateChangedObject(FacesContext, UIComponent, Object)}.
	 */
	@Test
	public final void testValidateWithDifferentValues() {
		try {
			// here a ValidatorException can occur
			validator.validate(mock(FacesContext.class), buildUnitComponent(designation, quantity, 
					unit, doValidation), price);
			switch (expectedResult) {
			case VALIDATION_DISABLED: // This is expected
				// validate that service.validatePrice() was NOT called				
				try {
					verify(service, never()).validatePrice(any(), any(), any());
				} catch (ExceptionService exception) {
					fail("ExceptionService was thrown and should not have with " + buildMessage() 
							+ ", exception=" + exception.getMessage());
				}			
				break;
			case SUCCESS: // This is expected
				// validate that service.validatePrice() was called				
				try {
					verify(service).validatePrice(any(), any(), any());
				} catch (ExceptionService exception) {
					fail("ExceptionService was thrown and should not have with " + buildMessage() 
							+ ", exception=" + exception.getMessage());
				}
				break;
			case FAILURE_PRICE_NULL: // This is NOT expected
			case FAILURE_PRICE_ZERO_OR_NEGATIVE: // This is NOT expected
			case FAILURE_OTHER_INPUTS_ARE_MISSING: // This is NOT expected
			case FAILURE_PRICE_OUT_OF_RANGE: // This is NOT expected
				fail("ValidatorException was not thrown and should have with " + buildMessage());
				break;
			}
		} catch (ValidatorException expectedException) {
			switch (expectedResult) {
			case VALIDATION_DISABLED: // This is NOT expected
			case SUCCESS: // This is NOT expected
				fail("ValidatorException was thrown and should not have with " + buildMessage() 
						+ ", " + expectedException.getFacesMessage().getSummary());		
				break;
			case FAILURE_PRICE_NULL: // This is expected
				assertEquals("ValidatorException was thrown, which is expected with " + buildMessage(), 
						getValueFromKey(MANDATORY), 
						expectedException.getFacesMessage().getSummary());		
				break;
			case FAILURE_PRICE_ZERO_OR_NEGATIVE: // This is expected
				assertEquals("ValidatorException was thrown, which is expected with " + buildMessage(), 
						getValueFromKey(NUMBER_GREATER_THAN_ZERO), 
						expectedException.getFacesMessage().getSummary());		
				break;
			case FAILURE_OTHER_INPUTS_ARE_MISSING: // This is expected
				assertEquals("ValidatorException was thrown, which is expected with " + buildMessage(), 
						getValueFromKey(UNIT_QUANTITY_DESIGNATION_MANDATORY), 
						expectedException.getFacesMessage().getSummary());		
				break;
			case FAILURE_PRICE_OUT_OF_RANGE: // This is expected
				assertEquals("ValidatorException was thrown, which is expected with " + buildMessage(), 
						getValueFromKey(PRICE_OUT_OF_RANGE), 
						expectedException.getFacesMessage().getSummary());		
				break;
			}
		}
	}

	// Static methods //------------------------------------------
	/**
	 * Reference a list of values, associated with its expected result, 
	 * to be used as parameters on constructor.
	 * Each set of values will be tested with the test 
	 * {@link TestValidatorPrice#testValidateWithDifferentValues()}.
	 * @return {@code Iterable<Object[]>} with the parameters.
	 */
	@Parameters
	public static Iterable<Object[]> valueToTest() {
		return Arrays.asList(new Object[][] {
			// The validation must not fail when disabled
			{null, null, null, null, false, ExpectedResult.VALIDATION_DISABLED},
			{buildDesignation(Unit.KILOGRAM, new BigDecimal("10")), new BigDecimal("956"), Unit.GRAM, 
				new BigDecimal("9.58"), false, ExpectedResult.VALIDATION_DISABLED},
			// The validation must succeed with correct values
			{buildDesignation(Unit.KILOGRAM, new BigDecimal("10")), new BigDecimal("956"), Unit.GRAM, 
				new BigDecimal("9.58"), true, ExpectedResult.SUCCESS},
			{buildDesignation(Unit.KILOGRAM, new BigDecimal("10")), new BigDecimal("1.1"), Unit.KILOGRAM, 
				new BigDecimal("10.56"), true, ExpectedResult.SUCCESS},
			// The validation must fail when price null and validation enabled
			{buildDesignation(Unit.KILOGRAM, new BigDecimal("10")), new BigDecimal("956"), Unit.GRAM, 
				null, true, ExpectedResult.FAILURE_PRICE_NULL},
			// The validation must fail when price 0 or negative and validation enabled
			{buildDesignation(Unit.KILOGRAM, new BigDecimal("10")), new BigDecimal("956"), Unit.GRAM, 
				new BigDecimal("0"), true, ExpectedResult.FAILURE_PRICE_ZERO_OR_NEGATIVE},
			{buildDesignation(Unit.KILOGRAM, new BigDecimal("10")), new BigDecimal("956"), Unit.GRAM, 
				new BigDecimal("-10.6"), true, ExpectedResult.FAILURE_PRICE_ZERO_OR_NEGATIVE},
			// The validation must fail when other inputs are missing and validation enabled
			{null, new BigDecimal("956"), Unit.GRAM, 
				new BigDecimal("9.58"), true, ExpectedResult.FAILURE_OTHER_INPUTS_ARE_MISSING},
			{buildDesignation(Unit.KILOGRAM, new BigDecimal("10")), null, Unit.GRAM, 
				new BigDecimal("9.58"), true, ExpectedResult.FAILURE_OTHER_INPUTS_ARE_MISSING},
			{buildDesignation(Unit.KILOGRAM, new BigDecimal("10")), new BigDecimal("956"), null, 
				new BigDecimal("9.58"), true, ExpectedResult.FAILURE_OTHER_INPUTS_ARE_MISSING},
			// The validation must fail when price is out of range and validation enabled
			{buildDesignation(Unit.KILOGRAM, new BigDecimal("10")), new BigDecimal("956"), Unit.GRAM, 
				new BigDecimal("256"), true, ExpectedResult.FAILURE_PRICE_OUT_OF_RANGE},
			{buildDesignation(Unit.KILOGRAM, new BigDecimal("10")), new BigDecimal("956"), Unit.GRAM, 
				new BigDecimal("7.56"), true, ExpectedResult.FAILURE_PRICE_OUT_OF_RANGE}
		});
	}

	/**
	 * Construct a {@link Designation} with a parameterized unit.
	 * @param unit the {@link Unit} to set.
	 * @param medianPrice the {@link BigDecimal} to set.
	 * @return the built {@link Designation}.
	 */
	private static Designation buildDesignation(Unit unit, BigDecimal medianPrice) {
		Designation designation = TestDesignation.generateDesignationWithIdNull();
		designation.setUnit(unit);
		designation.setMedianPrice(medianPrice);
		return designation;
	}

	// Helpers //-------------------------------------------------
	/**
	 * Build a stubbed {@link UIComponent} usable for tests.
	 * @param designation the {@link Designation}.
	 * @param quantity {@link BigDecimal} the quantity.
	 * @param unit the {@link Unit}.
	 * @param doValidation boolean determine if validation is enabled.
	 * @return the built {@link UIComponent}.
	 */
	private UIComponent buildUnitComponent(Designation designation, BigDecimal quantity, Unit unit, 
			boolean doValidation) {
		// Create UIComponent
		UIComponent component = new UIComponentBase() {
			@Override
			public String getFamily() {
				return "TestComponent";
			}
		};
		// Create UIInput unit
		UIInput uiInput1 = new UIInput();
		uiInput1.setValue(unit);
		// Set UIInput to UIComponent
		component.getAttributes().put(PARAMETER1.toString(), uiInput1);

		// Create UIInput quantity
		UIInput uiInput2 = new UIInput();
		uiInput2.setValue(quantity);
		// Set UIInput to UIComponent
		component.getAttributes().put(PARAMETER2.toString(), uiInput2);

		// Create UIInput designation
		UIInput uiInput3 = new UIInput();
		uiInput3.setValue(designation);
		// Set UIInput to UIComponent
		component.getAttributes().put(PARAMETER3.toString(), uiInput3);

		// Set doValidation to UIComponent
		component.getAttributes().put(PARAMETER4.toString(), doValidation);
		return component;
	}
	
	/**
	 * Construct a readable message to understand test's errors.
	 * @return String the message
	 */
	private String buildMessage() {
		return "designation=" + (designation != null ? (designation.getMedianPrice() 
				+ "$/" + designation.getUnit()) : "null") + ", quantity=" + quantity 
				+ ", unit=" + unit + ", price=" + price + ", doValidation=" + doValidation;
	}
}