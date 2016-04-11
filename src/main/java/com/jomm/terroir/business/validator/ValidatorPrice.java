package com.jomm.terroir.business.validator;

import static com.jomm.terroir.util.Constants.ResourceBundleError.NUMBER;
import static com.jomm.terroir.util.Constants.ResourceBundleError.PRICE_OUT_OF_RANGE;
import static com.jomm.terroir.util.Constants.ResourceBundleError.UNIT_NOT_CONVERTIBLE;
import static com.jomm.terroir.util.Constants.ResourceBundleError.UNIT_QUANTITY_DESIGNATION_MANDATORY;
import static com.jomm.terroir.util.Constants.View.PARAMETER1;
import static com.jomm.terroir.util.Constants.View.PARAMETER2;
import static com.jomm.terroir.util.Constants.View.PARAMETER3;
import static com.jomm.terroir.util.Resources.getValueFromKey;

import java.math.BigDecimal;
import java.text.MessageFormat;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import com.jomm.terroir.business.ServiceDesignation;
import com.jomm.terroir.business.model.Designation;
import com.jomm.terroir.util.Constants.Unit;
import com.jomm.terroir.util.exception.ExceptionService;

/**
 * This Class is the Validator relating to a price.
 * It implements {@link Validator} and defines its method {@code validate()},
 * that throws an {@link ValidatorException} if validation fails.
 * It is annotated {@link FacesValidator} for proper access from/to the view pages,
 * with {@code validator="validatorPrice"}.
 * @author Maic
 */
@FacesValidator("validatorPrice")
public class ValidatorPrice implements Validator {

	// Injected Fields //-----------------------------------------
	@Inject
	private ServiceDesignation service;

	// Variables //-----------------------------------------------
	private Unit unit;
	private BigDecimal quantity;
	private Designation designation;
	private BigDecimal price;

	// Methods //-------------------------------------------------
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if (value != null && component != null) {
			retrieveAndValidateParameters(component, value);
			// Validation
			BigDecimal pricePerUnit = price.divide(quantity);
			try {
				pricePerUnit = service.convertPriceIntoDesignationUnit(designation, pricePerUnit, unit);
			} catch (ExceptionService exception) {
				throw new ValidatorException(createMessage(exception.getMessage()), exception);
			}
			if (pricePerUnit == null) {
				// Conversion was not feasible
				if (!onlyOneUnitIsPIECE(unit, designation.getUnit())) {
					Object[] argument = {designation.getUnit().getLocalizedName()};
					String summary = MessageFormat.format(
							getValueFromKey(UNIT_NOT_CONVERTIBLE).replace("'", "''"), argument);
					throw new ValidatorException(createMessage(summary));
				}
			} else if (isOutOfRange(designation, pricePerUnit)) {
				// Price is too far from median price
				throw new ValidatorException(createMessage(getValueFromKey(PRICE_OUT_OF_RANGE)));
			}
		}
	}

	// Helpers //-------------------------------------------------
	/**
	 * Retrieve the parameters binded in a {@link UIComponent}.
	 * @param component {@link UIComponent}.
	 * @param value {@link Object}.
	 * @throws a {@link ValidatorException} if one of the parameters is {@code null}.
	 */
	private void retrieveAndValidateParameters(UIComponent component, Object value) 
			throws ValidatorException {
		// Retrieve price
		price = (BigDecimal) value;
		if (price.signum() != 1) {
			throw new ValidatorException(createMessage(getValueFromKey(NUMBER)));
		}
		// Retrieve component's parameters
		if (component.getAttributes().get(PARAMETER1.toString()) != null) {
			unit = (Unit) ((UIInput) component.getAttributes().get(PARAMETER1.toString())).getValue();
		}
		if (component.getAttributes().get(PARAMETER2.toString()) != null) {
			quantity = (BigDecimal) ((UIInput) component.getAttributes().get(PARAMETER2.toString())).getValue();
		}
		if (component.getAttributes().get(PARAMETER3.toString()) != null) {
			designation = (Designation) ((UIInput) component.getAttributes().get(PARAMETER3.toString())).getValue();
		}
		if (unit == null || quantity == null || designation == null) {
			throw new ValidatorException(createMessage(getValueFromKey(UNIT_QUANTITY_DESIGNATION_MANDATORY)));
		}
	}

	/**
	 * Compare the {@link com.jomm.terroir.business.model.Product}'s current price 
	 * with the {@link Designation}'s median price.
	 * @param designation {@link Designation} the designation.
	 * @param currentPrice {@link BigDecimal} the current price.
	 * @return true if the current price is out of range, false otherwise.
	 */
	private boolean isOutOfRange(Designation designation, BigDecimal currentPrice) {
		boolean outOfRange = true;
		try {
			if (currentPrice.compareTo(service.getMaxRangePrice(designation)) <= 0 
					&& currentPrice.compareTo(service.getMinRangePrice(designation)) >= 0) {
				outOfRange = false;
			}
		} catch (ExceptionService exception) {
			throw new ValidatorException(createMessage(exception.getMessage()), exception);
		}
		return outOfRange;
	}

	/**
	 * Determine if only one unit is {@link Unit#PIECE}.
	 * @param unit1 {@link Unit} the first unit.
	 * @param unit2 {@link Unit} the second unit.
	 * @return {@code true} if only one unit is {@link Unit#PIECE}, {@code false} otherwise.
	 */
	private boolean onlyOneUnitIsPIECE(Unit unit1, Unit unit2) {
		return (unit1 != Unit.PIECE && unit2 == Unit.PIECE) || (unit1 == Unit.PIECE && unit2 != Unit.PIECE);
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