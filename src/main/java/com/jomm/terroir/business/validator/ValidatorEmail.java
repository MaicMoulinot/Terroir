package com.jomm.terroir.business.validator;

import static com.jomm.terroir.util.Constants.Pattern.EMAIL;
import static com.jomm.terroir.util.Constants.ResourceBundleError.EMAIL_EXISTING;
import static com.jomm.terroir.util.Constants.ResourceBundleError.EMAIL_NOT_MATCHING_PATTERN;
import static com.jomm.terroir.util.Resources.getValueFromKey;

import java.text.MessageFormat;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import org.omnifaces.validator.ValueChangeValidator;

import com.jomm.terroir.business.ServiceUser;

/**
 * This Class is the Validator relating to an email.
 * It extends {@link ValueChangeValidator}, instead of implementing {@link javax.faces.validator.Validator}.
 * {@link ValueChangeValidator} performs the validation only when the submitted value has changed
 * compared to the model value, which avoid unnecessarily expensive service/DAO calls. 
 * It overrides the method {@code validateChangedObject()}, that throws an {@link ValidatorException} 
 * if the validation fails.
 * It relates to {@link Pattern} to define a correct email pattern,
 * and to {@link ServiceUser} to check if the email is already in use.
 * It is annotated {@link FacesValidator} for proper access from/to the view pages,
 * with {@code validator="validatorEmail"}.
 * @author Maic
 */
@FacesValidator("validatorEmail")
public class ValidatorEmail extends ValueChangeValidator {

	// Constants //-----------------------------------------------
	private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL.getRegex(), Pattern.CASE_INSENSITIVE);
	
	// Injected Fields //-----------------------------------------
	@Inject
	private ServiceUser serviceUser;
	
	// Methods //-------------------------------------------------
	@Override
	public void validateChangedObject(FacesContext context, UIComponent component, Object value) 
			throws ValidatorException {
		if (value != null) {
			String email = (String) value;
			if (!email.isEmpty()) {
				if (!EMAIL_PATTERN.matcher(email).matches()) {
					// Email address is invalid
					throw new ValidatorException(createMessage(getValueFromKey(EMAIL_NOT_MATCHING_PATTERN)));
				} else if (serviceUser.isExistingEmail(email)) {
					Object[] argument = {email};
					String summary = MessageFormat.format(getValueFromKey(EMAIL_EXISTING).replace("'", "''"), argument);
					throw new ValidatorException(createMessage(summary));
				}
			}
		}
	}
	
	// Helpers //-------------------------------------------------
	/**
	 * Instantiate a new {@link FacesMessage} with severity {@link FacesMessage#SEVERITY_ERROR} and detail {@code null}.
	 * @param summary String the message's summary.
	 * @return {@link FacesMessage} the message.
	 */
	private FacesMessage createMessage(String summary) {
		return new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, null);
	}
	
	// Tests only //----------------------------------------------
	/**
	 * This method should only be used in tests, so the visibility is set to default/package.
	 * @param serviceUser the serviceUser to set.
	 */
	void setTestServiceUser(ServiceUser serviceUser) {
		this.serviceUser = serviceUser;
	}
}