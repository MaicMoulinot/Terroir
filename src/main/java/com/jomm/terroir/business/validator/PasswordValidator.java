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

import com.jomm.terroir.util.Error;

/**
 * This Class is the Validator relating to a password.
 * It implements {@link Validator} and defines its method validate(),
 * that throws an {@link ValidatorException} if validation fails.
 * It relates to {@link ResourceBundle} to get proper {@link Error} messages,
 * and to {@link Pattern} to define a correct password pattern.
 * It is annotated {@link Named} for proper access from/to the view pages.
 * @author Maic
 */
@Named
public class PasswordValidator implements Validator {

	// Static constants
	private static final String PASSWORD_PARAMETER = "passwordParam";
	private static final String PASSWORDS_DONT_MATCH = "passwordsdifferent";
	private static final String FIELD_MANDATORY = "mandatory";
	private static final String PASSWORD_TOO_SIMPLE = "passwordunsecured";
	private static final String PASSWORD_RULES = "passwordrules";
	
	@Inject
	@Error
    private ResourceBundle resource;

	// Pattern for password
	public static final Pattern PASSWORD_PATTERN = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})");
	// (?=.*\d)		#   must contain one digit from 0-9
	// (?=.*[a-z])	#   must contain one lowercase character
	// (?=.*[A-Z])	#   must contain one uppercase character
	// (?=.*[@#$%])	#   must contain one special symbol in the list "@#$%"
	// .			#	match all previous conditions with
	// {6,20}		#	length is minimum of 6 characters and maximum of 20 characters

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		try {
			String password1 = (String) ((UIInput) component.getAttributes().get(PASSWORD_PARAMETER)).getValue();
			String password2 = (String) value;
			if (password1 == null || password2 == null) {
				// One password at least is lacking
				throw new ValidatorException(
						new FacesMessage(FacesMessage.SEVERITY_ERROR, resource.getString(FIELD_MANDATORY), null));
			} else if (!password2.equals(password1)) {
				// Passwords don't match
				throw new ValidatorException(
						new FacesMessage(FacesMessage.SEVERITY_ERROR, resource.getString(PASSWORDS_DONT_MATCH), null));
			} else {
				// Password doesn't match pattern
				if (!PASSWORD_PATTERN.matcher(password1).matches()) {
					throw new ValidatorException(
							new FacesMessage(FacesMessage.SEVERITY_ERROR, resource.getString(PASSWORD_TOO_SIMPLE), 
									resource.getString(PASSWORD_RULES)));
				}
			}
		} catch(Exception exception) {
			throw new ValidatorException(
					new FacesMessage(FacesMessage.SEVERITY_ERROR, exception.getMessage(), null));
		}
	}
}
