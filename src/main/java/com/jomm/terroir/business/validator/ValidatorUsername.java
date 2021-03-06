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
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import org.omnifaces.validator.ValueChangeValidator;

import com.jomm.terroir.business.ServiceUser;

/**
 * This Class is the Validator relating to an user name.
 * It extends {@link ValueChangeValidator}, instead of implementing {@link javax.faces.validator.Validator}.
 * {@link ValueChangeValidator} performs the validation only when the submitted value has changed
 * compared to the model value, which avoid unnecessarily expensive service/DAO calls. 
 * It overrides the method {@code validateChangedObject()}, that throws an {@link ValidatorException} 
 * if the validation fails.
 * It relates to {@link ServiceUser} to check if the user name is already in use.
 * It is annotated {@link FacesValidator} for proper access from/to the view pages,
 * with {@code validator="validatorUsername"}.
 * @author Maic
 */
@FacesValidator("validatorUsername")
public class ValidatorUsername extends ValueChangeValidator {
	
	// Constants //-----------------------------------------------
	private static final Pattern USERNAME_PATTERN = Pattern.compile(USERNAME.getRegex());
	
	// Injected Fields //-----------------------------------------
	@Inject
	private ServiceUser serviceUser;
	
	// Methods //-------------------------------------------------
	@Override
	public void validateChangedObject(FacesContext context, UIComponent component, Object value) 
			throws ValidatorException {
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
					String summary = MessageFormat.format(getValueFromKey(USER_NAME_EXISTING).replace("'", "''"), argument);
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