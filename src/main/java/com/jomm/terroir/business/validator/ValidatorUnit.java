package com.jomm.terroir.business.validator;

import static com.jomm.terroir.util.Constants.ResourceBundleError.MANDATORY_DESIGNATION;
import static com.jomm.terroir.util.Constants.ResourceBundleError.UNIT_NOT_CONVERTIBLE;
import static com.jomm.terroir.util.Constants.View.PARAMETER1;
import static com.jomm.terroir.util.Resources.getValueFromKey;

import java.text.MessageFormat;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.jomm.terroir.business.model.Designation;
import com.jomm.terroir.util.Constants.Unit;

/**
 * This Class is the Validator relating to the unit of a product.
 * It implements {@link Validator} and defines its method {@code validate()},
 * that throws an {@link ValidatorException} if validation fails.
 * It is annotated {@link FacesValidator} for proper access from/to the view pages,
 * with {@code validator="validatorUnit"}.
 * @author Maic
 */
@FacesValidator("validatorUnit")
public class ValidatorUnit implements Validator {

	// Methods //-------------------------------------------------
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		// Retrieve current unit
		Unit currentUnit = (Unit) value;
		if (currentUnit != null) {
			// Retrieve designation's unit
			Unit designationUnit = retrieveValueFromComponent(component);
			// Validation starts
			if (currentUnit != designationUnit
					&& !isConvertible(designationUnit, currentUnit)
					&& !atLeastOneIsPIECE(designationUnit, currentUnit)) {
				// Unit is not valid
				Object[] argument = {currentUnit.getLocalizedName(), designationUnit.getLocalizedName()};
				String summary = MessageFormat.format(
						getValueFromKey(UNIT_NOT_CONVERTIBLE).replace("'", "''"), argument);
				throw new ValidatorException(createMessage(summary));
			}
		}
	}

	// Helpers //-------------------------------------------------
	/**
	 * Determine if at least one unit is {@link Unit#PIECE}.
	 * @param unit1 {@link Unit} the first unit.
	 * @param unit2 {@link Unit} the second unit.
	 * @return {@code true} if at least one unit is {@link Unit#PIECE}, {@code false} otherwise.
	 */
	private boolean atLeastOneIsPIECE(Unit unit1, Unit unit2) {
		return unit1 == Unit.PIECE || unit2 == Unit.PIECE;
	}

	/**
	 * Determine if the {@code currentUnit} is convertible into the {@code designationUnit}.
	 * @param designationUnit {@link Unit} the designation's unit.
	 * @param currentUnit {@link Unit} the current unit.
	 * @return {@code true} if the conversion is possible, {@code false} otherwise.
	 */
	private boolean isConvertible(Unit designationUnit, Unit currentUnit) {
		boolean isConvertible = false;
		switch (designationUnit) {
		case LITER:
			if (currentUnit == Unit.MILLILITER) {
				isConvertible = true;
			}
			break;
		case KILOGRAM:
			if (currentUnit == Unit.MILLIGRAM || currentUnit == Unit.GRAM) {
				isConvertible = true;
			}
			break;
		default: // units non convertible
			break;
		}
		return isConvertible;
	}

	/**
	 * Retrieve the value from a {@link UIComponent}.
	 * @param component {@link UIComponent}.
	 * @return a {@link Unit} the value.
	 */
	private Unit retrieveValueFromComponent(UIComponent component) {
		Designation value = null;
		if (component != null && component.getAttributes().get(PARAMETER1.toString()) != null) {
			value = (Designation) ((UIInput) component.getAttributes().get(PARAMETER1.toString())).getValue();
		}
		if (value == null) {
			throw new ValidatorException(createMessage(getValueFromKey(MANDATORY_DESIGNATION)));
		}
		return value.getUnit();
	}

	/**
	 * Instantiate a new {@link FacesMessage} with severity {@link FacesMessage#SEVERITY_ERROR}.
	 * @param summary String the message's summary.
	 * @return {@link FacesMessage} the message.
	 */
	private FacesMessage createMessage(String summary) {
		return new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, null);
	}
}