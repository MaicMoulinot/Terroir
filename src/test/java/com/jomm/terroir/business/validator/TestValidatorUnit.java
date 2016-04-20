package com.jomm.terroir.business.validator;

import static com.jomm.terroir.util.Constants.ResourceBundleError.MANDATORY_DESIGNATION;
import static com.jomm.terroir.util.Constants.ResourceBundleError.UNIT_NOT_CONVERTIBLE;
import static com.jomm.terroir.util.Constants.View.PARAMETER1;
import static com.jomm.terroir.util.Resources.getValueFromKey;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.regex.Pattern;

import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.jomm.terroir.business.model.Designation;
import com.jomm.terroir.business.model.TestDesignation;
import com.jomm.terroir.util.Constants.Unit;

/**
 * This class is a Junit test case testing the {@code validate()} method of {@link ValidatorUnit}.
 * It is annotated {@link RunWith} {@link Parameterized} to allow the test case to run with different parameters.
 * Here, the parameters are different user names to be tested, and their expected results.
 * @author Maic
 */
@RunWith(Parameterized.class)
public class TestValidatorUnit {

	// Constants //-----------------------------------------------
	/** Enumeration of different possible results for {@code validate()} method of {@link ValidatorUnit}. */
	private enum ExpectedResult {
		/** The designation is lacking, thus the validation should fail. */
		FAILURE_DESIGNATION_NULL,
		/** The unit is not convertible into the designation's one, thus the validation should fail. */
		FAILURE_UNIT_NOT_CONVERTIBLE,
		/** The value is a valid unit, thus the validation should succeed. */
		SUCCESS
	};
	
	// Variables //-----------------------------------------------
	private ValidatorUnit validator;
	private Unit unit;
	private Designation designation;
	private ExpectedResult expectedResult;

	// Constructors //--------------------------------------------
	/**
	 * Constructor.
	 * Its parameter comes from all values from {@link TestValidatorUnit#valueToTest()}.
	 * @param unit the {@link Unit} to be tested.
	 * @param designation the {@link Designation} to be tested.
	 * @param expectedResult Result the expected result of the validation.
	 */
	public TestValidatorUnit(Unit unit, Designation designation, ExpectedResult expectedResult) {
		// Set the parameters for the test
		this.unit = unit;
		this.designation = designation;
		this.expectedResult = expectedResult;
		// Initialize the validator
		validator = new ValidatorUnit();
	}

	// Test methods //--------------------------------------------
	/**
	 * Test method {@link ValidatorUnit#validate(FacesContext, UIComponent, Object)}.
	 */
	@Test
	public final void testValidateWithDifferentValues() {
		String designationUnit = designation == null ? "designation=null" : designation.getUnit().toString();
		try {
			// here a ValidatorException can occur
			validator.validate(mock(FacesContext.class), buildUnitComponent(designation), unit);
			switch (expectedResult) {
			case SUCCESS: // This is expected
				assertTrue("ValidatorException is not thrown, which is expected with unit=" + unit 
						+ " and designation=" + designationUnit, true);				
				break;
			case FAILURE_DESIGNATION_NULL: // This is NOT expected
			case FAILURE_UNIT_NOT_CONVERTIBLE: // This is NOT expected
				fail("ValidatorException was not thrown and should have with unit=" + unit 
						+ " and designation=" + designationUnit);				
				break;
			}
		} catch (ValidatorException expectedException) {
			switch (expectedResult) {
			case SUCCESS: // This is NOT expected
				fail("ValidatorException was thrown and should not have with unit=" + unit 
						+ " and designation=" + designationUnit 
						+ ", " + expectedException.getFacesMessage().getSummary());		
				break;
			case FAILURE_DESIGNATION_NULL: // This is expected
				assertEquals("ValidatorException was thrown, which is expected with unit=" + unit 
						+ " and designation=" + designationUnit, 
						getValueFromKey(MANDATORY_DESIGNATION), 
						expectedException.getFacesMessage().getSummary());		
				break;
			case FAILURE_UNIT_NOT_CONVERTIBLE: // This is expected
		    	String[] splitWith0 = getValueFromKey(UNIT_NOT_CONVERTIBLE).split(Pattern.quote("{0}"));
		    	String[] splitWith1 = splitWith0[1].split(Pattern.quote("{1}"));
		    	String expectedSummary = expectedException.getFacesMessage().getSummary();
				assertTrue("ValidatorException was thrown, which is expected with unit=" + unit 
						+ ", designation=" + designationUnit + ", expectedSummary=" + expectedSummary 
						+ ", startsWith=" + splitWith0[0], expectedSummary.startsWith(splitWith0[0]));
				assertTrue("ValidatorException was thrown, which is expected with unit=" + unit 
						+ ", designation=" + designationUnit + ", expectedSummary=" + expectedSummary 
						+ ", contains=" + splitWith1[0], expectedSummary.contains(splitWith1[0]));
				assertTrue("ValidatorException was thrown, which is expected with unit=" + unit 
						+ ", designation=" + designationUnit + ", expectedSummary=" + expectedSummary 
						+ ", endsWith=" + splitWith1[1], expectedSummary.endsWith(splitWith1[1]));
				break;
			}
		}
	}

	// Static methods //------------------------------------------
	/**
	 * Reference a list of units, associated with their expected result to the test, 
	 * to be used as parameters on constructor.
	 * Each pair of passwords will be tested with the test 
	 * {@link TestValidatorUnit#testValidateWithDifferentValues()}.
	 * @return {@code Iterable<Object[]>} with the parameters.
	 */
	@Parameters
	public static Iterable<Object[]> valueToTest() {
		return Arrays.asList(new Object[][] {
			// The validation must not fail with null values
			{null, null, ExpectedResult.SUCCESS},
			{null, buildDesignation(Unit.PIECE), ExpectedResult.SUCCESS},
			// If the unit is not null, then then designation should not be null
			{Unit.PIECE, null, ExpectedResult.FAILURE_DESIGNATION_NULL},
			{Unit.LITER, null, ExpectedResult.FAILURE_DESIGNATION_NULL},
			// The validation must not fail when designation unit is PIECE
			{Unit.LITER, buildDesignation(Unit.PIECE), ExpectedResult.SUCCESS},
			{Unit.MILLIGRAM, buildDesignation(Unit.PIECE), ExpectedResult.SUCCESS},
			// The validation must not fail when unit is PIECE
			{Unit.PIECE, buildDesignation(Unit.PIECE), ExpectedResult.SUCCESS},
			{Unit.PIECE, buildDesignation(Unit.GRAM), ExpectedResult.SUCCESS},
			{Unit.PIECE, buildDesignation(Unit.MILLILITER), ExpectedResult.SUCCESS},
			// The validation must succeed with correct pair of values
			{Unit.LITER, buildDesignation(Unit.LITER), ExpectedResult.SUCCESS},
			{Unit.GRAM, buildDesignation(Unit.KILOGRAM), ExpectedResult.SUCCESS},
			{Unit.MILLIGRAM, buildDesignation(Unit.KILOGRAM), ExpectedResult.SUCCESS},//10
			{Unit.MILLILITER, buildDesignation(Unit.LITER), ExpectedResult.SUCCESS},
			// The validation must fail when unit is not convertible
			{Unit.MILLIGRAM, buildDesignation(Unit.LITER), ExpectedResult.FAILURE_UNIT_NOT_CONVERTIBLE},//12
			{Unit.LITER, buildDesignation(Unit.GRAM), ExpectedResult.FAILURE_UNIT_NOT_CONVERTIBLE},//13
			{Unit.KILOGRAM, buildDesignation(Unit.MILLILITER), ExpectedResult.FAILURE_UNIT_NOT_CONVERTIBLE}//14
		});
	}
	
	/**
	 * Construct a {@link Designation} with a parameterized unit.
	 * @param unit the {@link Unit} to set.
	 * @return the built {@link Designation}.
	 */
	private static Designation buildDesignation(Unit unit) {
		Designation designation = TestDesignation.generateDesignationWithIdNull();
		designation.setUnit(unit);
		return designation;
	}
	
	// Helpers //-------------------------------------------------
	/**
	 * Build a stubbed {@link UIComponent} usable for tests.
	 * @param designation the {@link Designation}.
	 * @return the built UIComponent.
	 */
	private UIComponent buildUnitComponent(Designation designation) {
		// Create UIComponent
		UIComponent component = new UIComponentBase() {
			@Override
			public String getFamily() {
				return "TestComponent";
			}
		};
		// Create UIInput
		UIInput uiInput = new UIInput();
		uiInput.setValue(designation);
		// Set UIInput to UIComponent
		component.getAttributes().put(PARAMETER1.toString(), uiInput);
		return component;
	}
}