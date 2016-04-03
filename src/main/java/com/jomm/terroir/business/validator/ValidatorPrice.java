package com.jomm.terroir.business.validator;

import static com.jomm.terroir.util.Constants.ResourceBundleError.DESIGNATION_INVALID;
import static com.jomm.terroir.util.Constants.ResourceBundleError.PRICE_OUT_OF_RANGE;
import static com.jomm.terroir.util.Constants.View.PARAMETER;
import static com.jomm.terroir.util.Resources.getValueFromKey;

import java.math.BigDecimal;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

import com.jomm.terroir.business.model.Designation;

/**
 * This Class is the Validator relating to a price.
 * It implements {@link Validator} and defines its method {@code validate()},
 * that throws an {@link ValidatorException} if validation fails.
 * It is annotated {@link Named} for proper access from/to the view pages, with
 * {@code f:validator binding="validatorPrice"}. It is not yet annotated 
 * {@link javax.faces.validator.FacesValidator} because validators are not injection targets in JSF2.2.
 * @author Maic
 */
@Named
public class ValidatorPrice implements Validator {
	
	// Constants //-----------------------------------------------
	private static final BigDecimal THIRTY_PER_CENT = new BigDecimal("30").divide(new BigDecimal("100"));
	
	// Methods //-------------------------------------------------
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if (value != null) {
			// Retrieve price
			BigDecimal price = (BigDecimal) value;
			// Retrieve designation
			Designation designation = retrieveValueFromComponent(component);
			// Validation
			if (designation == null || designation.getMedianPrice() == null) {
				// Designation was not correctly set in the UIComponent
				throw new ValidatorException(createMessage(getValueFromKey(DESIGNATION_INVALID)));
			} else if (isOutOfRange(designation.getMedianPrice(), price)) {
				// Price is too far from median price
				throw new ValidatorException(createMessage(getValueFromKey(PRICE_OUT_OF_RANGE)));
			}
		}
	}

	// Helpers //-------------------------------------------------
	/**
	 * Retrieve the value from a {@link UIComponent}.
	 * @param component {@link UIComponent}.
	 * @return a {@link Designation} the value.
	 */
	private Designation retrieveValueFromComponent(UIComponent component) {
		Designation value = null;
		if (component != null && component.getAttributes().get(PARAMETER.toString()) != null) {
			value = (Designation) ((UIInput) component.getAttributes().get(PARAMETER.toString())).getValue();
		}
		return value;
	}
	
	/**
	 * Compare the {@link com.jomm.terroir.business.model.Product}'s current price 
	 * with the {@link Designation}'s median price.
	 * If the current price is more or less than {@link ValidatorPrice#THIRTY_PER_CENT} the median price,
	 * than the current price is out of range.
	 * @param medianPrice {@link BigDecimal} the median price.
	 * @param currentPrice {@link BigDecimal} the current price.
	 * @return true if the current price is out of range, false otherwise.
	 */
	private boolean isOutOfRange(BigDecimal medianPrice, BigDecimal currentPrice) {
		boolean outOfRange = false;
		BigDecimal range = medianPrice.multiply(THIRTY_PER_CENT);
		if (currentPrice.compareTo(medianPrice.add(range)) > 0 
				|| currentPrice.compareTo(medianPrice.subtract(range)) < 0) {
			outOfRange = true;
		}
		return outOfRange;
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