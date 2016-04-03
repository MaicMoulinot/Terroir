package com.jomm.terroir.business.validator;

import static com.jomm.terroir.util.Constants.ResourceBundleError.EMAIL_EXISTING;
import static com.jomm.terroir.util.Constants.ResourceBundleError.EMAIL_NOT_MATCHING_PATTERN;
import static com.jomm.terroir.util.Constants.Pattern.EMAIL;
import static com.jomm.terroir.util.Resources.getValueFromKey;

import java.text.MessageFormat;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import com.jomm.terroir.business.ServiceUser;

/**
 * This Class is the Validator relating to an email.
 * It implements {@link Validator} and defines its method {@code validate()},
 * that throws an {@link ValidatorException} if validation fails.
 * It relates to {@link Pattern} to define a correct email pattern,
 * and to {@link ServiceUser} to check if the email is already in use.
 * It is annotated {@link Named} for proper access from/to the view pages, with
 * {@code f:validator binding="validatorEmail"}. It is not yet annotated 
 * {@link javax.faces.validator.FacesValidator} because validators are not injection targets in JSF2.2.
 * @author Maic
 */
@Named
public class ValidatorEmail implements Validator {

	// Constants //-----------------------------------------------
	private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL.getRegex(), Pattern.CASE_INSENSITIVE);
	
	// Injected Fields //-----------------------------------------
	@Inject
	private ServiceUser serviceUser;
	
	// Methods //-------------------------------------------------
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
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