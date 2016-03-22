package com.jomm.terroir.business.validator;

import static com.jomm.terroir.util.Constants.ResourceBundleError.LENGTH_AT_LEAST_6_CHARACTERS;
import static com.jomm.terroir.util.Constants.ResourceBundleError.USER_NAME_EXISTING;
import static com.jomm.terroir.util.Resources.getValueFromKey;

import java.text.MessageFormat;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import com.jomm.terroir.business.ServiceUser;

/**
 * This Class is the Validator relating to an user name.
 * It implements {@link Validator} and defines its method {@code validate()},
 * that throws an {@link ValidatorException} if validation fails.
 * It relates to {@link ServiceUser} to check if the user name is already in use.
 * It is annotated {@link Named} for proper access from/to the view pages, with
 * {@code f:validator binding="validatorUsername"}. It is not yet annotated 
 * {@link javax.faces.validator.FacesValidator} because validators are not injection targets in JSF2.2.
 * @author Maic
 */
@Named
public class ValidatorUsername implements Validator {

	@Inject
	private ServiceUser userService;

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if (value != null) {
			String userName = (String) value;
			if (!userName.isEmpty()) {
				// Minimum length = 6
				if (userName.length() < 6) {
					throw new ValidatorException(createMessage(getValueFromKey(LENGTH_AT_LEAST_6_CHARACTERS)));
				}
				// Existing in database
				if (userService.isExistingUserName(userName)) {
					Object[] argument = {userName};
					String summary = MessageFormat.format(getValueFromKey(USER_NAME_EXISTING), argument);
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