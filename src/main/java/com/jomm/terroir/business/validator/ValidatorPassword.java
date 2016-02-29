package com.jomm.terroir.business.validator;

import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import com.jomm.terroir.util.BundleError;
import com.jomm.terroir.util.Constants;

/**
 * This Class is the Validator relating to a password.
 * It implements {@link Validator} and defines its method <code>validate()</code>,
 * that throws an {@link ValidatorException} if validation fails.
 * It relates to {@link ResourceBundle} to get proper {@link BundleError} messages,
 * and to {@link Pattern} to define a correct password pattern.
 * It is annotated {@link Named} for proper access from/to the view pages, with
 * <code>f:validator binding="#{validatorPassword}"</code>. It is not yet annotated 
 * {@link javax.faces.validator.FacesValidator} because validators are not injection targets in JSF2.2.
 * @author Maic
 */
@Named
public class ValidatorPassword implements Validator {

	@Inject
	@BundleError
	ResourceBundle resource;

	// Pattern for password
	static final Pattern PASSWORD_PATTERN = 
			Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})");
	// (?=.*\d)		#   must contain one digit from 0-9
	// (?=.*[a-z])	#   must contain one lowercase character
	// (?=.*[A-Z])	#   must contain one uppercase character
	// (?=.*[@#$%])	#   must contain one special symbol in the list "@#$%"
	// .			#	match all previous conditions with
	// {6,20}		#	length is minimum of 6 characters and maximum of 20 characters

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		// Retrieve password1
		String password1 = retrieveValueFromComponent(component);
		// Retrieve password2
		String password2 = (value != null) ? (String) value : null;
		// Validation
		if (isLacking(password1) || isLacking(password2)) {
			// One password at least is lacking
			throw new ValidatorException(
					new FacesMessage(FacesMessage.SEVERITY_ERROR, 
							resource.getString(Constants.FIELD_MANDATORY), null));
		} else if (!password2.matches(password1)) {
			// Passwords don't match
			throw new ValidatorException(
					new FacesMessage(FacesMessage.SEVERITY_ERROR, 
							resource.getString(Constants.PASSWORDS_DONT_MATCH), null));
		} else if (!PASSWORD_PATTERN.matcher(password1).matches()) {
			// Password doesn't match pattern
			throw new ValidatorException(
					new FacesMessage(FacesMessage.SEVERITY_ERROR, 
							resource.getString(Constants.PASSWORD_TOO_SIMPLE), 
							resource.getString(Constants.PASSWORD_RULES)));
		}
	}
	
	/**
	 * Determine if the password is null or empty.
	 * @param password String.
	 * @return true if the password is lacking null or empty, false otherwise.
	 */
	private boolean isLacking(String password) {
		return password == null || password.isEmpty();
	}
	
	/**
	 * Retrieve the value from a {@link UIComponent}.
	 * @param component {@link UIComponent}.
	 * @return a String the value.
	 */
	private String retrieveValueFromComponent(UIComponent component) {
		String value = null;
		if (component != null && component.getAttributes().get(Constants.PASSWORD_PARAMETER) != null) {
			value = (String) ((UIInput) component.getAttributes().get(Constants.PASSWORD_PARAMETER)).getValue();
		}
		return value;
	}
}