package com.jomm.terroir.business.validator;

import static com.jomm.terroir.util.Constants.ResourceBundleError.INTEGER;
import static com.jomm.terroir.util.Constants.ResourceBundleError.AVAILABILITY;
import static com.jomm.terroir.util.Constants.View.PARAMETER1;
import static com.jomm.terroir.util.Resources.getValueFromKey;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * This Class is the Validator relating to a product's availability.
 * It implements {@link Validator} and defines its method {@code validate()},
 * that throws an {@link ValidatorException} if validation fails.
 * It is annotated {@link FacesValidator} for proper access from/to the view pages,
 * with {@code validator="validatorAvailability"}.
 * @author Maic
 */
@FacesValidator("validatorAvailability")
public class ValidatorAvailability implements Validator {

	// Methods //-------------------------------------------------
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		// Retrieve isActive
		Boolean isActive = retrieveValueFromComponent(component);
		// Retrieve availability
		Integer availability = (value != null) ? (Integer) value : null;
		if (availability != null) {
			// Validation
			if (availability < 0) {
				// Quantity cannot be negative
				throw new ValidatorException(createMessage(getValueFromKey(INTEGER)));
			} else if (availability == 0 && isActive) {
				// Quantity cannot be zero if status is active
				throw new ValidatorException(createMessage(getValueFromKey(AVAILABILITY)));
			}
		}
	}

	// Helpers //-------------------------------------------------
	/**
	 * Retrieve the value from a {@link UIComponent}.
	 * @param component {@link UIComponent}.
	 * @return a Boolean the value.
	 */
	private Boolean retrieveValueFromComponent(UIComponent component) {
		Boolean value = null;
		if (component != null && component.getAttributes().get(PARAMETER1.toString()) != null) {
			value = (Boolean) ((UIInput) component.getAttributes().get(PARAMETER1.toString())).getValue();
		}
		return value;
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