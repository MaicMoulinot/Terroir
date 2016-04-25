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

import java.math.BigDecimal;
import java.math.RoundingMode;

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
 * It relates to {@link ServiceDesignation} to check if the price is correct.
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
	public void validate(FacesContext context, UIComponent component, Object value) 
			throws ValidatorException {
		if (isValidationEnabled(component, value)) {
			// Retrieve all values
			retrieveAndValidateValue(value);
			retrieveAndValidateParameters(component);
			// Compute price per unit
			BigDecimal pricePerUnit = calculatePricePerUnit(quantity, price);
			try {
				// Verify price
				if (!service.validatePrice(designation, pricePerUnit, unit)) {
					throw new ValidatorException(createMessage(getValueFromKey(PRICE_OUT_OF_RANGE)));
				}
			} catch (ExceptionService exception) {
				throw new ValidatorException(createMessage(exception.getMessage()), exception);
			}
		}
	}

	// Helpers //-------------------------------------------------
	/**
	 * Determine if the validation should proceed.
	 * If {@code value} is not {@code null}, the validation always occurs.
	 * Otherwise, the validation occurs according to the {@code parameter4} stocked in {@code component}.
	 * @param component {@link UIComponent}.
	 * @param value {@link Object}.
	 */
	private boolean isValidationEnabled(UIComponent component, Object value) {
		// Retrieve the component's parameters
		boolean doValidation = true;
		if (value == null && component.getAttributes().get(PARAMETER4.toString()) != null) {
			doValidation = (Boolean) component.getAttributes().get(PARAMETER4.toString());
		}
		return doValidation;
	}

	/**
	 * Retrieve and validate the value, an {@link Object}.
	 * @param value {@link Object}.
	 * @throws ValidatorException if the value is {@code null}, or if it not positive.
	 */
	private void retrieveAndValidateValue(Object value) throws ValidatorException {
		// Retrieve price
		if (value == null) {
			// This is because we cannot use required=true in jsf page
			throw new ValidatorException(createMessage(getValueFromKey(MANDATORY)));
		} else {
			price = (BigDecimal) value;
			if (price.signum() != 1) {
				throw new ValidatorException(createMessage(getValueFromKey(NUMBER_GREATER_THAN_ZERO)));
			}
		}
	}

	/**
	 * Retrieve and validate the parameters binded in a {@link UIComponent}.
	 * @param component {@link UIComponent}.
	 * @throws ValidatorException if one of the parameters is {@code null}.
	 */
	private void retrieveAndValidateParameters(UIComponent component) throws ValidatorException {
		// Retrieve the component's parameters
		if (component.getAttributes().get(PARAMETER1.toString()) != null) {
			unit = (Unit) ((UIInput) component.getAttributes().get(PARAMETER1.toString())).getValue();
		}
		if (component.getAttributes().get(PARAMETER2.toString()) != null) {
			quantity = (BigDecimal) ((UIInput) component.getAttributes().get(PARAMETER2.toString())).getValue();
		}
		if (component.getAttributes().get(PARAMETER3.toString()) != null) {
			designation = (Designation) ((UIInput) component.getAttributes().get(PARAMETER3.toString())).getValue();
		}
		// Validate the parameters
		if (unit == null || quantity == null || designation == null) {
			throw new ValidatorException(createMessage(getValueFromKey(UNIT_QUANTITY_DESIGNATION_MANDATORY)));
		}
	}

	/**
	 * Compute the price per unit using the {@code quantity} and {@code price}.
	 * @param quantity {@link BigDecimal} the quantity.
	 * @param price {@link BigDecimal} the price.
	 * @return a {@link BigDecimal} the computed price per unit.
	 */
	private BigDecimal calculatePricePerUnit(BigDecimal quantity, BigDecimal price) {
		return (quantity != null && price != null) ? price.divide(quantity, 4, RoundingMode.HALF_UP) : null;
	}

	/**
	 * Instantiate a new {@link FacesMessage} with severity {@link FacesMessage#SEVERITY_ERROR}.
	 * @param summary String the message's summary.
	 * @return {@link FacesMessage} the message.
	 */
	private FacesMessage createMessage(String summary) {
		return new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, null);
	}

	// Tests only //----------------------------------------------
	/**
	 * This method should only be used in tests, so the visibility is set to default/package.
	 * @param service the service to set.
	 */
	void setTestServiceDesignation(ServiceDesignation service) {
		this.service = service;
	}
}