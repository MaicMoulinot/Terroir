package com.jomm.terroir.business.validator;

import static com.jomm.terroir.util.Constants.ResourceBundleError.EMAIL_EXISTING;
import static com.jomm.terroir.util.Constants.ResourceBundleError.EMAIL_NOT_MATCHING_PATTERN;
import static com.jomm.terroir.util.Constants.Pattern.VALID_EMAIL;
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

	// Pattern for password
	static final Pattern EMAIL_PATTERN = Pattern.compile(VALID_EMAIL.getRegex(), Pattern.CASE_INSENSITIVE);

	@Inject
	private ServiceUser userService;

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if (value != null) {
			String email = (String) value;
			if (!email.isEmpty()) {
				if (!EMAIL_PATTERN.matcher(email).matches()) {
					// Email address is invalid
					throw new ValidatorException(createMessage(getValueFromKey(EMAIL_NOT_MATCHING_PATTERN)));
				} else if (userService.isExistingEmail(email)) {
					Object[] argument = {email};
					String summary = MessageFormat.format(getValueFromKey(EMAIL_EXISTING), argument);
					throw new ValidatorException(createMessage(summary));
				}
			}
		}
	}
	
	/**
	 * Instantiate a new {@link FacesMessage} with severity {@link FacesMessage#SEVERITY_ERROR} and detail {@code null}.
	 * @param summary String the message's summary.
	 * @return {@link FacesMessage} the message.
	 */
	private FacesMessage createMessage(String summary) {
		return new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, null);
	}
}