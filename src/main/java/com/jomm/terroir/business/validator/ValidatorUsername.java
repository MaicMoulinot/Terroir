package com.jomm.terroir.business.validator;

import static com.jomm.terroir.util.Constants.Pattern.USERNAME;
import static com.jomm.terroir.util.Constants.ResourceBundleError.LENGTH_BETWEEN_5_AND_15;
import static com.jomm.terroir.util.Constants.ResourceBundleError.USER_NAME_EXISTING;
import static com.jomm.terroir.util.Constants.ResourceBundleError.USER_NAME_NOT_MATCHING_PATTERN;
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
	
	// Constants //-----------------------------------------------
	private static final Pattern USERNAME_PATTERN = Pattern.compile(USERNAME.getRegex());
	
	// Injected Fields //-----------------------------------------
	@Inject
	private ServiceUser serviceUser;
	
	// Methods //-------------------------------------------------
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if (value != null) {
			String userName = (String) value;
			if (!userName.isEmpty()) {
				if (userName.length() < 5 || userName.length() > 15) {
					// Length too short or too long
					throw new ValidatorException(createMessage(getValueFromKey(LENGTH_BETWEEN_5_AND_15)));
				} else if (!USERNAME_PATTERN.matcher(userName).matches()) {
					// Doesn't match pattern
					throw new ValidatorException(createMessage(getValueFromKey(USER_NAME_NOT_MATCHING_PATTERN)));
				} else if (serviceUser.isExistingUserName(userName)) {
					// Existing in database
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
	
	// Tests //---------------------------------------------------
	/**
	 * This method should only be used in tests, so the visibility is set to default/package.
	 * @param serviceUser the serviceUser to set.
	 */
	void setTestServiceUser(ServiceUser serviceUser) {
		this.serviceUser = serviceUser;
	}
}